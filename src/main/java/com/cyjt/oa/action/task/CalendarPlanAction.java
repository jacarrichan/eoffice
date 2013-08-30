package com.cyjt.oa.action.task;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.util.DateUtil;
import com.cyjt.core.util.StringUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.task.CalendarPlan;
import com.cyjt.oa.model.task.PlanInfo;
import com.cyjt.oa.service.system.AppUserService;
import com.cyjt.oa.service.task.CalendarPlanService;

public class CalendarPlanAction extends BaseAction {

	@Resource
	private CalendarPlanService calendarPlanService;
	private CalendarPlan calendarPlan;

	@Resource
	private AppUserService appUserService;
	private List<CalendarPlan> list;
	private Long planId;

	public Long getPlanId() {
		return this.planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public CalendarPlan getCalendarPlan() {
		return this.calendarPlan;
	}

	public void setCalendarPlan(CalendarPlan calendarPlan) {
		this.calendarPlan = calendarPlan;
	}

	public List<CalendarPlan> getList() {
		return this.list;
	}

	public void setList(List<CalendarPlan> list) {
		this.list = list;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());

		if (this.getRequest().getParameter("Q_assignerId_L_EQ") == null) {
			filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
					.toString());
		}

		List<CalendarPlan> list = this.calendarPlanService.getAll(filter);

		Type type = new TypeToken<List<CalendarPlan>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String display() {
		QueryFilter filter = new QueryFilter(this.getRequest());

		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addSorted("planId", "desc");
		List<CalendarPlan> list = this.calendarPlanService.getAll(filter);
		this.getRequest().setAttribute("calendarList", list);
		return "display";
	}

	public String today() {
		PagingBean pb = new PagingBean(this.start.intValue(),
				this.limit.intValue());
		List<CalendarPlan> list = this.calendarPlanService.getTodayPlans(
				ContextUtil.getCurrentUserId(), pb);
		List<PlanInfo> planList = new ArrayList<PlanInfo>();

		for (CalendarPlan plan : list) {
			planList.add(new PlanInfo(plan));
		}

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Type type = new TypeToken<List<PlanInfo>>() {
		}.getType();
		buff.append(gson.toJson(planList, type));
		buff.append("}");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.calendarPlanService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		CalendarPlan calendarPlan = this.calendarPlanService.get(this.planId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(calendarPlan));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.calendarPlan.getPlanId() == null) {
			this.calendarPlan.setStatus(CalendarPlan.STATUS_UNFINISHED);

			AppUser appUser = ContextUtil.getCurrentUser();

			this.calendarPlan.setAssignerId(appUser.getUserId());
			this.calendarPlan.setAssignerName(appUser.getFullname());

			this.calendarPlanService.save(this.calendarPlan);
		} else {
			CalendarPlan cp = this.calendarPlanService.get(this.calendarPlan
					.getPlanId());
			try {
				BeanUtil.copyNotNullProperties(cp, this.calendarPlan);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
			this.calendarPlanService.save(cp);
		}

		this.setJsonString("{success:true}");
		return "success";
	}

	public String my() {
		HttpServletRequest request = this.getRequest();
		String datafn = request.getParameter("action");

		Date startDate = null;
		Date endDate = null;
		Date reqEndDate;
		if ("month".equals(datafn)) {
			String monthday = request.getParameter("monthday");
			try {
				Date reqDate = DateUtils.parseDate(monthday,
						new String[] { "MM/dd/yyyy" });
				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDate);

				cal.set(5, 1);

				startDate = DateUtil.setStartDay(cal).getTime();

				cal.add(2, 1);
				cal.add(5, -1);

				endDate = DateUtil.setEndDay(cal).getTime();
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		} else if ("day".equals(datafn)) {
			String day = request.getParameter("day");
			this.logger.info("day:" + day);
			try {
				Date reqDay = DateUtils.parseDate(day,
						new String[] { "MM/dd/yyyy" });

				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDay);

				startDate = DateUtil.setStartDay(cal).getTime();

				cal.add(2, 1);
				cal.add(5, -1);

				endDate = DateUtil.setEndDay(cal).getTime();
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		} else if ("week".equals(datafn)) {
			String startweek = request.getParameter("startweek");
			String endweek = request.getParameter("endweek");
			try {
				Date reqStartWeek = DateUtils.parseDate(startweek,
						new String[] { "MM/dd/yyyy" });
				Date reqEndWeek = DateUtils.parseDate(endweek,
						new String[] { "MM/dd/yyyy" });
				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartWeek);

				startDate = DateUtil.setStartDay(cal).getTime();
				cal.setTime(reqEndWeek);

				endDate = DateUtil.setEndDay(cal).getTime();
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		} else if ("period".equals(datafn)) {
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			try {
				Date reqStartDate = DateUtils.parseDate(start,
						new String[] { "MM/dd/yyyy" });
				reqEndDate = DateUtils.parseDate(end,
						new String[] { "MM/dd/yyyy" });

				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartDate);

				startDate = DateUtil.setStartDay(cal).getTime();

				cal.setTime(reqEndDate);

				endDate = DateUtil.setEndDay(cal).getTime();
			} catch (Exception ex) {
				this.logger.info(ex.getMessage());
			}
		} else {
			this.jsonString = "{success:false,errors:'there's enough arguments to generate data'}";
		}

		StringBuffer sb = new StringBuffer();

		List<CalendarPlan> planList = this.calendarPlanService.getByPeriod(
				ContextUtil.getCurrentUserId(), startDate, endDate);

		sb.append("{success:true,totalCount:").append(planList.size())
				.append(",records:[");

		for (CalendarPlan plan : planList) {
			sb.append("{id:'").append(plan.getPlanId()).append("',");

			String subject = plan.getContent();
			if (subject.length() > 12) {
				subject = subject.substring(1, 12) + "...";
			}

			Date endTime = plan.getEndTime();
			if (endTime == null) {
				Calendar curCal = Calendar.getInstance();
				curCal.add(1, 50);
				endTime = curCal.getTime();
			}

			Date startTime = plan.getStartTime();
			if (this.start == null) {
				Calendar curCal = Calendar.getInstance();
				startTime = curCal.getTime();
			}

			sb.append("subject:'").append(StringUtil.convertQuot(subject))
					.append("',");
			sb.append("description:'")
					.append(StringUtil.convertQuot(plan.getContent()))
					.append("',");
			sb.append("startdate:'").append(DateUtil.formatEnDate(startTime))
					.append("',");
			sb.append("enddate:'").append(DateUtil.formatEnDate(endTime))
					.append("',");
			sb.append("color:'").append(plan.getColor()).append("',");
			sb.append("parent:'0',");
			sb.append("priority:'").append(plan.getUrgent()).append("'},");
		}

		if (planList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]}");

		this.jsonString = sb.toString();
		return "success";
	}
}
