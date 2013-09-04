package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.communicate.MailDao;
import com.palmelf.eoffice.model.communicate.Mail;
import com.palmelf.eoffice.service.communicate.MailService;

public class MailServiceImpl extends BaseServiceImpl<Mail> implements
		MailService {
	private MailDao dao;

	public MailServiceImpl(MailDao dao) {
		super(dao);
		this.dao = dao;
	}
}
