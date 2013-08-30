package com.cyjt.oa.action.system;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.util.AppUtil;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.system.ReportParam;
import com.cyjt.oa.model.system.ReportTemplate;
import com.cyjt.oa.service.system.ReportParamService;
import com.cyjt.oa.service.system.ReportTemplateService;

import flexjson.JSONSerializer;

public class ReportTemplateAction extends BaseAction {
	private String uploadPath = AppUtil.getAppAbsolutePath() + "/attachFiles/";

	@Resource
	private ReportTemplateService reportTemplateService;
	private ReportTemplate reportTemplate;

	@Resource
	private ReportParamService reportParamService;
	private Long reportId;

	public Long getReportId() {
		return this.reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public ReportTemplate getReportTemplate() {
		return this.reportTemplate;
	}

	public void setReportTemplate(ReportTemplate reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<ReportTemplate> list = this.reportTemplateService.getAll(filter);

		Type type = new TypeToken<List<ReportTemplate>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String checkKey() {
		this.getRequest().getParameter("Q_reportKey_S_EQ");

		QueryFilter filter = new QueryFilter(this.getRequest());
		this.reportTemplateService.getAll(filter);

		new TypeToken() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.reportTemplate = (this.reportTemplateService.get(new Long(
						id)));
				List<ReportParam> list = this.reportParamService
						.findByRepTemp(new Long(id));
				for (ReportParam rp : list) {
					this.reportParamService.remove(rp);
				}

				File file = new File(this.uploadPath
						+ this.reportTemplate.getReportLocation());
				File parent = file.getParentFile();
				this.deleteFile(parent);

				this.reportTemplateService.remove(new Long(id));
			}

		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		ReportTemplate reportTemplate = this.reportTemplateService
				.get(this.reportId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(reportTemplate));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.reportTemplate.getReportId() == null) {
			this.reportTemplate.setCreatetime(new Date());
			this.reportTemplate.setUpdatetime(new Date());
			this.reportTemplateService.save(this.reportTemplate);
		} else {
			ReportTemplate old = this.reportTemplateService
					.get(this.reportTemplate.getReportId());

			if (!old.getReportLocation()
					.toString()
					.trim()
					.equals(this.reportTemplate.getReportLocation().toString()
							.trim())) {
				File file = new File(this.uploadPath + old.getReportLocation());

				this.deleteFile(file.getParentFile());
			}

			try {
				BeanUtil.copyNotNullProperties(old, this.reportTemplate);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			old.setUpdatetime(new Date());

			this.reportTemplateService.save(old);
		}

		this.setJsonString("{success:true}");
		return "success";
	}

	private void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File file2 : files) {
					this.deleteFile(file2);
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！\n");
		}
	}

	public String load() {
		String strReportId = this.getRequest().getParameter("reportId");
		if (StringUtils.isNotEmpty(strReportId)) {
			List list = this.reportParamService.findByRepTemp(new Long(
					strReportId));
			new JSONSerializer();

			Gson gson = new Gson();
			StringBuffer sb = new StringBuffer();
			sb.append(gson.toJson(list));

			this.setJsonString("{success:true,data:" + sb.toString() + "}");
		} else {
			this.setJsonString("{success:false}");
		}
		return "success";
	}

	public String submit() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");

		Map map = this.getRequest().getParameterMap();
		Iterator it = map.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String[] value = (String[]) entry.getValue();
			String v = value[0];

			if ((v == null) || (v.equals(""))) {
				v = "%";
			} else {
				try {
					dateformat.parse(v.trim());
				} catch (ParseException e) {
					v = "%" + v.trim() + "%";
				}

			}

			sb.append("&" + key + "=" + v);
		}
		this.setJsonString("{success:true,data:'" + sb.toString() + "'}");
		return "success";
	}
}
