package com.cyjt.oa.dao.communicate.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.communicate.MailDao;
import com.cyjt.oa.model.communicate.Mail;

public class MailDaoImpl extends BaseDaoImpl<Mail> implements MailDao {
	public MailDaoImpl() {
		super(Mail.class);
	}
}
