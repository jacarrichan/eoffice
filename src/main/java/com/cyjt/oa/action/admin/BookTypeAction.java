package com.cyjt.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.admin.BookType;
import com.cyjt.oa.service.admin.BookService;
import com.cyjt.oa.service.admin.BookTypeService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class BookTypeAction extends BaseAction {

	@Resource
	private BookTypeService bookTypeService;
	private BookType bookType;

	@Resource
	private BookService bookService;
	private Long typeId;

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public BookType getBookType() {
		return this.bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BookType> list = this.bookTypeService.getAll(filter);
		Type type = new TypeToken<List<BookType>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String tree() {
		String method = getRequest().getParameter("method");
		List<BookType> list = this.bookTypeService.getAll();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		if (StringUtils.isNotEmpty(method)) {
			sb.append("[");
		} else {
			i++;
			sb.append("[{id:'0',text:'图书类别',expanded:true,children:[");
		}
		for (BookType bookType : list) {
			sb.append("{id:'" + bookType.getTypeId() + "',text:'"
					+ bookType.getTypeName() + "',leaf:true},");
		}
		if (list.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		if (i == 0)
			sb.append("]");
		else {
			sb.append("]}]");
		}
		setJsonString(sb.toString());
		return "success";
	}

	public String remove() {
		Long typeId = Long.valueOf(getRequest().getParameter("typeId"));
		setBookType(this.bookTypeService.get(typeId));
		if (this.bookType != null) {
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_bookType.typeId_L_EQ", typeId.toString());
			List<?> list = this.bookService.getAll(filter);
			if (list.size() > 0) {
				this.jsonString = "{success:false,message:'该类型下还有图书，请将图书移走后再删除！'}";
				return "success";
			}
			this.bookTypeService.remove(typeId);
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_bookType.typeId_L_EQ", id);
				List<?> list = this.bookService.getAll(filter);
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'该类型下还有图书，请将图书移走后再删除！'}";
					return "success";
				}
				this.bookTypeService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		BookType bookType = this.bookTypeService.get(this.typeId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(bookType));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		this.bookTypeService.save(this.bookType);
		setJsonString("{success:true}");
		return "success";
	}
}
