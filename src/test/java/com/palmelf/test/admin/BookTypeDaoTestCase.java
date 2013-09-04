package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.BookTypeDao;
import com.palmelf.eoffice.model.admin.BookType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class BookTypeDaoTestCase extends BaseTestCase {

	@Resource
	private BookTypeDao bookTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		BookType bookType = new BookType();

		this.bookTypeDao.save(bookType);
	}
}
