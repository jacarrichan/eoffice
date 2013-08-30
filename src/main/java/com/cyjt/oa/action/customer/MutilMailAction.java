package com.cyjt.oa.action.customer;

import com.google.gson.Gson;
import com.cyjt.core.engine.MailEngine;
import com.cyjt.core.util.AppUtil;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.customer.Customer;
import com.cyjt.oa.model.customer.CustomerMail;
import com.cyjt.oa.model.customer.Provider;
import com.cyjt.oa.model.system.Company;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.service.customer.CustomerService;
import com.cyjt.oa.service.customer.ProviderService;
import com.cyjt.oa.service.system.CompanyService;
import com.cyjt.oa.service.system.FileAttachService;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MutilMailAction extends BaseAction {

	@Resource
	private MailEngine mailEngine;

	@Resource
	private ProviderService providerService;

	@Resource
	private FileAttachService fileAttachService;

	@Resource
	private CustomerService customerService;

	@Resource
	private CompanyService companyService;
	private CustomerMail customerMail;

	public CustomerMail getCustomerMail() {
		return this.customerMail;
	}

	public void setCustomerMail(CustomerMail customerMail) {
		this.customerMail = customerMail;
	}

	public String send() {
		Short type = this.customerMail.getType();
		String ids = this.customerMail.getIds();
		String files = this.customerMail.getFiles();
		List atFiles = new ArrayList();
		List fileName = new ArrayList();
		if (StringUtils.isNotEmpty(files)) {
			String[] fIds = files.split(",");
			for (int i = 0; i < fIds.length; i++) {
				FileAttach fileAttach = this.fileAttachService.get(new Long(
						fIds[i]));
				File file = new File(getSession().getServletContext()
						.getRealPath("/attachFiles/")
						+ "/"
						+ fileAttach.getFilePath());
				fileName.add(fileAttach.getFileName());
				atFiles.add(file);
			}
		}
		String[] id = ids.split(",");
		List toss = new ArrayList();
		if (type.shortValue() == 0) {
			for (int i = 0; i < id.length; i++) {
				Customer customer = this.customerService.get(new Long(id[i]));
				toss.add(customer.getEmail());
			}
		}
		if (type.shortValue() == 1) {
			for (int i = 0; i < id.length; i++) {
				Provider provider = this.providerService.get(new Long(id[i]));
				toss.add(provider.getEmail());
			}
		}
		String from = null;
		String cc = null;
		String htmlMsgContent = this.customerMail.getMailContent();
		String subject = this.customerMail.getSubject();
		String[] st = new String[0];
		String[] attachedFileNames = (String[]) fileName.toArray(st);
		File[] f = new File[0];
		File[] attachedFiles = (File[]) atFiles.toArray(f);
		String replyTo = null;
		String[] tos = (String[]) toss.toArray(st);
		if (tos.length > 0) {
			Map configs = AppUtil.getSysConfig();
			if ((StringUtils.isNotEmpty((String) configs.get("host")))
					&& (StringUtils
							.isNotEmpty((String) configs.get("username")))
					&& (StringUtils
							.isNotEmpty((String) configs.get("password")))
					&& (StringUtils.isNotEmpty((String) configs.get("from")))) {
				this.mailEngine.setFrom((String) configs.get("from"));
				this.mailEngine.sendMimeMessage(from, tos, cc, replyTo,
						subject, htmlMsgContent, attachedFileNames,
						attachedFiles, false);
				setJsonString("{success:true}");
			} else {
				setJsonString("{success:false,message:'未配置好邮箱配置!'}");
			}
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}

	public String loadVm() {
		VelocityEngine velocityEngine = (VelocityEngine) AppUtil
				.getBean("velocityEngine");
		String templateLocation = "mail/sendMsg.vm";
		Map model = new HashMap();
		model.put("appUser", ContextUtil.getCurrentUser());
		List list = this.companyService.findCompany();
		if (list.size() > 0) {
			Company company = (Company) list.get(0);
			if (company != null) {
				model.put("company", company);
			}
			String pageHtml = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, templateLocation, model);
			Gson gson = new Gson();
			setJsonString("{success:true,data:" + gson.toJson(pageHtml) + "}");
		} else {
			setJsonString("{success:false,message:'你的公司信息还不完整！请填写好公司信息!'}");
		}
		return "success";
	}
}
