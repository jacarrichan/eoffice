package com.cyjt.oa.dao.admin;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.admin.BookSn;
import java.util.List;

public abstract interface BookSnDao extends BaseDao<BookSn> {
	public abstract List<BookSn> findByBookId(Long paramLong);

	public abstract List<BookSn> findBorrowByBookId(Long paramLong);

	public abstract List<BookSn> findReturnByBookId(Long paramLong);
}
