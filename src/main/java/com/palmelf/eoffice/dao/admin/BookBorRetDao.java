package com.palmelf.eoffice.dao.admin;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.admin.BookBorRet;

import java.util.List;

public abstract interface BookBorRetDao extends BaseDao<BookBorRet> {
	public abstract BookBorRet getByBookSnId(Long paramLong);

	public abstract List getBorrowInfo(PagingBean paramPagingBean);

	public abstract List getReturnInfo(PagingBean paramPagingBean);

	public abstract Long getBookBorRetId(Long paramLong);
}
