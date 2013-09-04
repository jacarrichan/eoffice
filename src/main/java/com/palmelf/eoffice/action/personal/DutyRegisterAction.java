package com.palmelf.eoffice.action.personal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.util.DateUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.personal.Duty;
import com.palmelf.eoffice.model.personal.DutyRegister;
import com.palmelf.eoffice.model.personal.DutySection;
import com.palmelf.eoffice.model.personal.DutySystem;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.personal.DutyRegisterService;
import com.palmelf.eoffice.service.personal.DutySectionService;
import com.palmelf.eoffice.service.personal.DutyService;
import com.palmelf.eoffice.service.personal.DutySystemService;
import com.palmelf.eoffice.service.system.AppUserService;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class DutyRegisterAction extends BaseAction {

	@Resource
	private DutyRegisterService dutyRegisterService;

	@Resource
	private DutyService dutyService;

	@Resource
	private DutySystemService dutySystemService;

	@Resource
	private DutySectionService dutySectionService;

	@Resource
	private AppUserService appUserService;
	private DutyRegister dutyRegister;
	private Long registerId;

	public Long getRegisterId() {
		return this.registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public DutyRegister getDutyRegister() {
		return this.dutyRegister;
	}

	public void setDutyRegister(DutyRegister dutyRegister) {
		this.dutyRegister = dutyRegister;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<DutyRegister> list = this.dutyRegisterService.getAll(filter);

		Type type = new TypeToken<List<DutyRegister>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.excludeFieldsWithoutExposeAnnotation().create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String person() {
		QueryFilter filter = new QueryFilter(getRequest());

		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addSorted("registerDate", "desc");

		List<DutyRegister> list = this.dutyRegisterService.getAll(filter);

		Type type = new TypeToken<List<DutyRegister>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.excludeFieldsWithoutExposeAnnotation().create();

		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String today() {
		StringBuffer sb = new StringBuffer();

		DutySystem dutySystem = null;

		Duty duty = this.dutyService.getCurUserDuty(ContextUtil
				.getCurrentUserId());

		if (duty != null)
			dutySystem = duty.getDutySystem();
		else {
			dutySystem = this.dutySystemService.getDefaultDutySystem();
		}

		if (dutySystem == null) {
			setJsonString("{success:true,exception:'尚未为用户设置排班，请联系管理员!'}");
		} else {
			AppUser curUser = ContextUtil.getCurrentUser();

			String dutySetting = dutySystem.getSystemSetting();

			String[] day7Sections = dutySetting.split("[|]");

			Calendar curCal = Calendar.getInstance();

			int curDay = curCal.get(7);

			String[] curDaySections = day7Sections[(curDay - 1)].split("[,]");

			sb.append("{success:true,result:[");
			for (int i = 0; i < curDaySections.length; i++) {
				if ("-".equals(curDaySections[i])) {
					continue;
				}
				DutySection dutySection = this.dutySectionService.get(new Long(
						curDaySections[i]));

				DutyRegister signInReg = this.dutyRegisterService
						.getTodayUserRegister(curUser.getUserId(),
								DutyRegister.SIGN_IN,
								dutySection.getSectionId());
				DutyRegister signOffReg = this.dutyRegisterService
						.getTodayUserRegister(curUser.getUserId(),
								DutyRegister.SIGN_OFF,
								dutySection.getSectionId());
				if (i > 0)
					sb.append(",");
				sb.append("{sectionId:'").append(dutySection.getSectionId())
						.append("',systemName:'")
						.append(dutySection.getSectionName())
						.append("',startSignin:'")
						.append(dutySection.getStartSignin1())
						.append("',dutyStartTime:'")
						.append(dutySection.getDutyStartTime1())
						.append("',endSignin:'")
						.append(dutySection.getEndSignin1())
						.append("',earlyOffTime:'")
						.append(dutySection.getEarlyOffTime1())
						.append("',dutyEndTime:'")
						.append(dutySection.getDutyEndTime1())
						.append("',signOutTime:'")
						.append(dutySection.getSignOutTime1()).append("'");

				if (signInReg != null) {
					sb.append(",signInTime:'")
							.append(signInReg.getRegisterTime())
							.append("',signInFlag:'")
							.append(signInReg.getRegFlag())
							.append("',allowSignIn:'0'");
				} else {
					sb.append(",signInTime:'',signInFlag:''");

					Calendar startSignInCal = Calendar.getInstance();
					startSignInCal.setTime(dutySection.getStartSignin());
					DateUtil.copyYearMonthDay(startSignInCal, curCal);

					Calendar endSignInCal = Calendar.getInstance();
					endSignInCal.setTime(dutySection.getEndSignin());
					DateUtil.copyYearMonthDay(endSignInCal, curCal);

					int startCmpResult = curCal.compareTo(startSignInCal);
					int endCmpResult = curCal.compareTo(endSignInCal);
					if ((startCmpResult >= 0) && (endCmpResult <= 0))
						sb.append(",allowSignIn:'1'");
					else if (startCmpResult < 0)
						sb.append(",allowSignIn:'-1'");
					else {
						sb.append(",allowSignIn:'0'");
					}

				}

				if (signOffReg != null) {
					sb.append(",signOffTime:'")
							.append(signOffReg.getRegisterTime())
							.append("',signOffFlag:'")
							.append(signOffReg.getRegFlag())
							.append("',allowSignOff:'0'");
				} else {
					sb.append(",signOffTime:'',signOffFlag:''");

					Calendar startSignOffCal = Calendar.getInstance();
					startSignOffCal.setTime(dutySection.getEarlyOffTime());
					DateUtil.copyYearMonthDay(startSignOffCal, curCal);

					Calendar endSignOffCal = Calendar.getInstance();
					endSignOffCal.setTime(dutySection.getSignOutTime());
					DateUtil.copyYearMonthDay(endSignOffCal, curCal);

					int startCmpResult = curCal.compareTo(startSignOffCal);
					int endCmpResult = curCal.compareTo(endSignOffCal);

					if ((startCmpResult >= 0) && (endCmpResult <= 0))
						sb.append(",allowSignOff:'1'");
					else if (startCmpResult < 0)
						sb.append(",allowSignOff:'-1'");
					else {
						sb.append(",allowSignOff:'0'");
					}
				}
				sb.append("}");
			}
			sb.append("]}");

			setJsonString(sb.toString());
		}
		this.dutySystemService.evict(dutySystem);

		return "success";
	}

	public String signIn() {
		String sectionId = getRequest().getParameter("sectionId");
		this.dutyRegisterService.signInOff(new Long(sectionId),
				DutyRegister.SIGN_IN, ContextUtil.getCurrentUser(), new Date());
		this.jsonString = "{success:true}";
		return "success";
	}

	public String signOff() {
		String sectionId = getRequest().getParameter("sectionId");
		this.dutyRegisterService
				.signInOff(new Long(sectionId), DutyRegister.SIGN_OFF,
						ContextUtil.getCurrentUser(), new Date());
		this.jsonString = "{success:true}";
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.dutyRegisterService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		DutyRegister dutyRegister = this.dutyRegisterService
				.get(this.registerId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(dutyRegister));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		HttpServletRequest request = getRequest();

		String userIds = request.getParameter("userIds");
		Long sectionId = new Long(request.getParameter("sectionId"));
		Short inOffFlag = new Short(request.getParameter("inOffFlag"));

		Date registerDate = DateUtil.parseDate(request
				.getParameter("registerDate"));

		String[] uIds = userIds.split("[,]");
		for (int i = 0; i < uIds.length; i++) {
			AppUser appUser = this.appUserService.get(new Long(uIds[i]));
			this.dutyRegisterService.signInOff(sectionId, inOffFlag, appUser,
					registerDate);
		}
		setJsonString("{success:true}");
		return "success";
	}
}
