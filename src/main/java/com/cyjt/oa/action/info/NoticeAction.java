package com.cyjt.oa.action.info;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.info.Notice;
import com.cyjt.oa.service.info.NoticeService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public class NoticeAction extends BaseAction {

	@Resource
	private NoticeService noticeService;
	private Notice notice;
	private List<Notice> list;
	private Long noticeId;

	public List<Notice> getList() {
		return this.list;
	}

	public void setList(List<Notice> list) {
		this.list = list;
	}

	public Long getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<Notice> list = this.noticeService.getAll(filter);

		Type type = new TypeToken<List<Notice>>() {
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

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.noticeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Notice notice = this.noticeService.get(this.noticeId);

		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				new String[] { "effectiveDate" });
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				new String[] { "expirationDate" });

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[] { "class" })
				.serialize(notice));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.noticeService.save(this.notice);
		setJsonString("{success:true}");
		return "success";
	}

	public String search() {
		PagingBean pb = getInitPagingBean();
		String searchContent = getRequest().getParameter("searchContent");
		List<Notice> list = this.noticeService.findBySearch(searchContent, pb);
		Type type = new TypeToken<List<Notice>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String display() {
		PagingBean pb = new PagingBean(0, 8);
		List<Notice> list = this.noticeService.findBySearch(null, pb);
		getRequest().setAttribute("noticeList", list);
		return "display";
	}

	public String scroller() {
		PagingBean pb = new PagingBean(0, 8);
		List<Notice> list = this.noticeService.findBySearch(null, pb);
		getRequest().setAttribute("noticeList", list);
		return "scroller";
	}
}
