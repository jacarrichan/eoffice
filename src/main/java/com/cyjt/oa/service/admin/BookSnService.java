package com.cyjt.oa.service.admin;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.admin.BookSn;
import java.util.List;

public abstract interface BookSnService extends BaseService<BookSn> {
	public abstract List<BookSn> findByBookId(Long paramLong);

	public abstract List<BookSn> findBorrowByBookId(Long paramLong);

	public abstract List<BookSn> findReturnByBookId(Long paramLong);
}
