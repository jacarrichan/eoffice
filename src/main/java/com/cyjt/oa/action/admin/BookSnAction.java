package com.cyjt.oa.action.admin;

import com.google.gson.Gson;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.BookSn;
import com.cyjt.oa.service.admin.BookSnService;
import flexjson.JSONSerializer;
import java.util.List;
import javax.annotation.Resource;

public class BookSnAction extends BaseAction {

	@Resource
	private BookSnService bookSnService;
	private BookSn bookSn;
	private Long bookSnId;

	public Long getBookSnId() {
		return this.bookSnId;
	}

	public void setBookSnId(Long bookSnId) {
		this.bookSnId = bookSnId;
	}

	public BookSn getBookSn() {
		return this.bookSn;
	}

	public void setBookSn(BookSn bookSn) {
		this.bookSn = bookSn;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BookSn> list = this.bookSnService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		buff.append(serializer.exclude(new String[] { "class" })
				.serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.bookSnService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		BookSn bookSn = this.bookSnService.get(this.bookSnId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bookSn));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String getSn() {
		List<BookSn> list = null;
		this.bookSn = new BookSn();
		Long bookId = Long.valueOf(0L);
		bookId = Long.valueOf(getRequest().getParameter("bookId"));
		list = this.bookSnService.findByBookId(bookId);
		StringBuffer buff = new StringBuffer("[");
		for (BookSn bookSn : list) {
			buff.append("['" + bookSn.getBookSnId() + "','"
					+ bookSn.getBookSn() + "'],");
		}
		if (list.size() != 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}

	public String getBorrowSn() {
		List<BookSn> list = null;
		this.bookSn = new BookSn();
		Long bookId = Long.valueOf(0L);
		bookId = Long.valueOf(getRequest().getParameter("bookId"));
		list = this.bookSnService.findBorrowByBookId(bookId);
		StringBuffer buff = new StringBuffer("[");
		for (BookSn bookSn : list) {
			buff.append("['" + bookSn.getBookSnId() + "','"
					+ bookSn.getBookSn() + "'],");
		}
		if (list.size() != 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}

	public String getReturnSn() {
		List<BookSn> list = null;
		this.bookSn = new BookSn();
		Long bookId = Long.valueOf(0L);
		bookId = Long.valueOf(getRequest().getParameter("bookId"));
		list = this.bookSnService.findReturnByBookId(bookId);
		StringBuffer buff = new StringBuffer("[");
		for (BookSn bookSn : list) {
			buff.append("['" + bookSn.getBookSnId() + "','"
					+ bookSn.getBookSn() + "'],");
		}
		if (list.size() != 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}

	@Override
	public String save() {
		this.bookSnService.save(this.bookSn);
		setJsonString("{success:true}");
		return "success";
	}
}
