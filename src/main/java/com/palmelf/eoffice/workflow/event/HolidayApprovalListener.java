package com.palmelf.eoffice.workflow.event;

import com.palmelf.core.util.AppUtil;
import com.palmelf.eoffice.model.info.ShortMessage;
import com.palmelf.eoffice.model.personal.ErrandsRegister;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.info.ShortMessageService;
import com.palmelf.eoffice.service.personal.ErrandsRegisterService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;
import org.jbpm.api.model.OpenProcessInstance;

public class HolidayApprovalListener implements EventListener {
	private Log logger = LogFactory.getLog(HolidayApprovalListener.class);
	Short choice;

	public void notify(EventListenerExecution execution) throws Exception {
		if (this.logger.isDebugEnabled()) {
			this.logger
					.info("enter the HolidayApprovalListener notify method...");
		}

		OpenProcessInstance pi = execution.getProcessInstance();

		Long dateId = (Long) pi.getVariable("dateId");

		String superOption = (String) pi.getVariable("superOption");

		if (dateId != null) {
			ErrandsRegisterService erService = (ErrandsRegisterService) AppUtil
					.getBean("errandsRegisterService");
			ShortMessageService smService = (ShortMessageService) AppUtil
					.getBean("shortMessageService");
			ErrandsRegister errandsRegister = erService.get(dateId);

			if (errandsRegister != null) {
				errandsRegister.setStatus(this.choice);
				errandsRegister.setApprovalOption(superOption);
				erService.save(errandsRegister);

				smService.save(AppUser.SYSTEM_USER, errandsRegister.getUserId()
						.toString(), "你的请假申请  （" + errandsRegister.getDescp()
						+ "），最终审批意见：" + superOption, ShortMessage.MSG_TYPE_SYS);
			}
		}
	}
}
