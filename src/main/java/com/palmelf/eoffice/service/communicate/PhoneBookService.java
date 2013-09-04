package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.communicate.PhoneBook;

import java.util.List;

public abstract interface PhoneBookService extends BaseService<PhoneBook> {
	public abstract List<PhoneBook> sharedPhoneBooks(String paramString1,
			String paramString2, PagingBean paramPagingBean);
}
