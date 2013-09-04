package com.palmelf.eoffice.dao.communicate;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.communicate.PhoneBook;

import java.util.List;

public abstract interface PhoneBookDao extends BaseDao<PhoneBook> {
	public abstract List<PhoneBook> sharedPhoneBooks(String paramString1,
			String paramString2, PagingBean paramPagingBean);
}
