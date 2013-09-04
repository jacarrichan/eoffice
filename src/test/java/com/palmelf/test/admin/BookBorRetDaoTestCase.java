package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.BookBorRetDao;
import com.palmelf.eoffice.model.admin.BookBorRet;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class BookBorRetDaoTestCase extends BaseTestCase {

	@Resource
	private BookBorRetDao bookBorRetDao;

	@Test
	@Rollback(false)
	public void add() {
		BookBorRet bookBorRet = new BookBorRet();

		this.bookBorRetDao.save(bookBorRet);
	}
}
