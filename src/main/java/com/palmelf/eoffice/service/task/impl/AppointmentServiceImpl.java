package com.palmelf.eoffice.service.task.impl;

import com.palmelf.core.engine.MailEngine;
import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.task.AppointmentDao;
import com.palmelf.eoffice.model.task.Appointment;
import com.palmelf.eoffice.service.task.AppointmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

public class AppointmentServiceImpl extends BaseServiceImpl<Appointment>
		implements AppointmentService {

	@Resource
	private MailEngine mailEngine;
	private AppointmentDao dao;

	public AppointmentServiceImpl(AppointmentDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List showAppointmentByUserId(PagingBean pb) {
		return this.dao.showAppointmentByUserId(pb);
	}

	public void warningAppointment() {
		List<Appointment> list = this.dao.warningAppointment();
		for (Appointment apm : list) {
			String email = apm.getAppUser().getEmail();
			String phone = apm.getAppUser().getPhone();

			if ((apm.getIsMsg().intValue() == 1) && (!email.isEmpty())) {
				String tempPath = "mail/flowMail.vm";
				Map hashMap = new HashMap();
				String subject = "来自未央区城管办公系统的待办任务提醒";
				this.mailEngine.sendTemplateMail(tempPath, hashMap, subject,
						email, new String[] { apm.getInviteEmails() }, null,
						null, null, null, true);
			}

			if (apm.getIsMobile().intValue() != 1)
				continue;
			phone.isEmpty();
		}
	}
}
