package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.MailDao;
import com.cyjt.oa.model.communicate.Mail;
import com.cyjt.oa.service.communicate.MailService;

public class MailServiceImpl extends BaseServiceImpl<Mail> implements
		MailService {
	private MailDao dao;

	public MailServiceImpl(MailDao dao) {
		super(dao);
		this.dao = dao;
	}
}
