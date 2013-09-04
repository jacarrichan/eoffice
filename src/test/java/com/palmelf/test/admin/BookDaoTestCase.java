package com.palmelf.test.admin;

import com.google.gson.Gson;
import com.palmelf.eoffice.dao.admin.BookDao;
import com.palmelf.eoffice.model.admin.Book;
import com.palmelf.test.BaseTestCase;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class BookDaoTestCase extends BaseTestCase {

	@Resource
	private BookDao bookDao;

	@Test
	@Rollback(false)
	public void add() {
		Book book = new Book();

		this.bookDao.save(book);
	}

	@Test
	public void Testt() {
		List list = this.bookDao.getAll();
		Gson gson = new Gson();
		System.out.println(gson.toJson(list));
	}
}
