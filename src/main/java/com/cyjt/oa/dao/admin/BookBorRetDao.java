package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.admin.BookBorRet;
import java.util.List;

public abstract interface BookBorRetDao extends BaseDao<BookBorRet> {
	public abstract BookBorRet getByBookSnId(Long paramLong);

	public abstract List getBorrowInfo(PagingBean paramPagingBean);

	public abstract List getReturnInfo(PagingBean paramPagingBean);

	public abstract Long getBookBorRetId(Long paramLong);
}
