package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.BookSnDao;
import com.palmelf.eoffice.model.admin.BookSn;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class BookSnDaoTestCase extends BaseTestCase {

	@Resource
	private BookSnDao bookSnDao;

	@Test
	@Rollback(false)
	public void add() {
		BookSn bookSn = new BookSn();

		this.bookSnDao.save(bookSn);
	}
}
