package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.BookBorRetDao;
import com.cyjt.oa.model.admin.BookBorRet;
import com.cyjt.test.BaseTestCase;
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
