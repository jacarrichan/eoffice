package com.palmelf.eoffice.service.communicate.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.communicate.PhoneBookDao;
import com.palmelf.eoffice.model.communicate.PhoneBook;
import com.palmelf.eoffice.service.communicate.PhoneBookService;

import java.util.List;

public class PhoneBookServiceImpl extends BaseServiceImpl<PhoneBook> implements
		PhoneBookService {
	private PhoneBookDao dao;

	public PhoneBookServiceImpl(PhoneBookDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<PhoneBook> sharedPhoneBooks(String fullname, String ownerName,
			PagingBean pb) {
		return this.dao.sharedPhoneBooks(fullname, ownerName, pb);
	}
}
