package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.admin.BookDao;
import com.cyjt.oa.model.admin.Book;
import java.util.List;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {
	public BookDaoImpl() {
		super(Book.class);
	}

	public List<Book> findByTypeId(Long typeId, PagingBean pb) {
		String hql = "from Book b where b.bookType.typeId=?";
		Object[] params = { typeId };
		return findByHql("from Book b where b.bookType.typeId=?", params, pb);
	}
}
