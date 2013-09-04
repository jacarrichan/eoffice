package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.admin.Book;

import java.util.List;

public abstract interface BookDao extends BaseDao<Book> {
	public abstract List<Book> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);
}
