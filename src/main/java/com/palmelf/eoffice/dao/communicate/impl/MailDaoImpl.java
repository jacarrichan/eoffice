package com.palmelf.eoffice.dao.communicate.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.communicate.MailDao;
import com.palmelf.eoffice.model.communicate.Mail;

public class MailDaoImpl extends BaseDaoImpl<Mail> implements MailDao {
	public MailDaoImpl() {
		super(Mail.class);
	}
}
