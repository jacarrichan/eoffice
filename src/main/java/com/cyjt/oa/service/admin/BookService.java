package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.admin.Book;
import java.util.List;

public abstract interface BookService extends BaseService<Book> {
	public abstract List<Book> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);
}
