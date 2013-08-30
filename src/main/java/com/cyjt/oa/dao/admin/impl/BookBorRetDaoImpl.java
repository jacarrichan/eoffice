package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.admin.BookBorRetDao;
import com.cyjt.oa.model.admin.BookBorRet;
import java.util.List;

public class BookBorRetDaoImpl extends BaseDaoImpl<BookBorRet> implements
		BookBorRetDao {
	public BookBorRetDaoImpl() {
		super(BookBorRet.class);
	}

	public BookBorRet getByBookSnId(Long bookSnId) {
		String hql = "from BookBorRet bookBorRet where bookBorRet.bookSn.bookSnId=?";
		Object[] params = { bookSnId };
		return findByHql(hql, params).get(0);
	}

	public List<BookBorRet> getBorrowInfo(PagingBean pb) {
		String hql = "select bookBorRet from BookBorRet bookBorRet,BookSn bookSn where bookBorRet.bookSn.bookSnId=bookSn.bookSnId and bookSn.status=1";
		return findByHql(hql, null, pb);
	}

	public List<BookBorRet> getReturnInfo(PagingBean pb) {
		String hql = "select bookBorRet from BookBorRet bookBorRet,BookSn bookSn where bookBorRet.bookSn.bookSnId=bookSn.bookSnId and bookSn.status=0";
		return findByHql(hql, null, pb);
	}

	public Long getBookBorRetId(Long snId) {
		String hql = "from BookBorRet vo where vo.bookSn.bookSnId=?";
		Object[] objs = { snId };
		List<BookBorRet> list = findByHql(hql, objs);
		if (list.size() == 1) {
			return (list.get(0)).getRecordId();
		}
		return null;
	}
}
