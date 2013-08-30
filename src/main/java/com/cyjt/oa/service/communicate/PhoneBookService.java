package com.cyjt.oa.service.communicate;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.communicate.PhoneBook;
import java.util.List;

public abstract interface PhoneBookService extends BaseService<PhoneBook> {
	public abstract List<PhoneBook> sharedPhoneBooks(String paramString1,
			String paramString2, PagingBean paramPagingBean);
}
