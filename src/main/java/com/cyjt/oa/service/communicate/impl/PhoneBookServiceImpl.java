package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.communicate.PhoneBookDao;
import com.cyjt.oa.model.communicate.PhoneBook;
import com.cyjt.oa.service.communicate.PhoneBookService;
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
