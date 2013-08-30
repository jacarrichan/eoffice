package com.cyjt.test.admin;

import com.google.gson.Gson;
import com.cyjt.oa.dao.admin.BookDao;
import com.cyjt.oa.model.admin.Book;
import com.cyjt.test.BaseTestCase;
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
