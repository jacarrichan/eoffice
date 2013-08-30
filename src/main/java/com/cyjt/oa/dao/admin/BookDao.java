package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.admin.Book;
import java.util.List;

public abstract interface BookDao extends BaseDao<Book> {
	public abstract List<Book> findByTypeId(Long paramLong,
			PagingBean paramPagingBean);
}
