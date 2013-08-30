package com.cyjt.oa.dao.communicate;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.communicate.PhoneBook;
import java.util.List;

public abstract interface PhoneBookDao extends BaseDao<PhoneBook> {
	public abstract List<PhoneBook> sharedPhoneBooks(String paramString1,
			String paramString2, PagingBean paramPagingBean);
}
