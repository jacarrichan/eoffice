package com.palmelf.eoffice.service.admin;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.admin.BookSn;

import java.util.List;

public abstract interface BookSnService extends BaseService<BookSn> {
	public abstract List<BookSn> findByBookId(Long paramLong);

	public abstract List<BookSn> findBorrowByBookId(Long paramLong);

	public abstract List<BookSn> findReturnByBookId(Long paramLong);
}
