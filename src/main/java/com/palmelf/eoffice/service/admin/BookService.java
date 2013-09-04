package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.admin.Book;

import java.util.List;

public abstract interface BookService extends BaseService<Book> {
	public abstract List<Book> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);
}
