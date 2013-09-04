package com.palmelf.eoffice.action.info;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.StringUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.News;
import com.palmelf.eoffice.model.info.NewsType;
import com.palmelf.eoffice.service.info.NewsService;
import com.palmelf.eoffice.service.info.NewsTypeService;

import flexjson.JSONSerializer;

public class NewsAction extends BaseAction {

	@Resource
	private NewsService newsService;

	@Resource
	private NewsTypeService newsTypeService;
	private News news;
	private List<News> list;
	private Long newsId;
	private Long typeId;
	private NewsType newsType;

	public List<News> getList() {
		return this.list;
	}

	public void setList(List<News> list) {
		this.list = list;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public News getNews() {
		return this.news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public NewsType getNewsType() {
		return this.newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		List<News> list = this.newsService.getAll(filter);

		Type type = new TypeToken<List<News>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.newsService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		News news = this.newsService.get(this.newsId);

		JSONSerializer serializer = new JSONSerializer();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(serializer.exclude(new String[] { "class", "newsComments" })
				.serialize(news));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		String isDeskNews = this.getRequest().getParameter("isDeskImage");
		if (StringUtils.isNotEmpty(isDeskNews)) {
			this.news.setIsDeskImage(News.ISDESKNEWS);
		} else {
			this.news.setIsDeskImage(News.NOTDESKNEWS);
		}
		News old = new News();
		Boolean isNew = Boolean.valueOf(false);
		if (this.news.getNewsId() == null) {
			isNew = Boolean.valueOf(true);
		}
		if (this.news.getTypeId() != null) {
			this.setNewsType(this.newsTypeService.get(this.news.getTypeId()));
			this.news.setNewsType(this.newsType);
		}
		if (isNew.booleanValue()) {
			this.news.setCreatetime(new Date());
			this.news.setUpdateTime(new Date());
			this.news.setReplyCounts(Integer.valueOf(0));
			this.news.setViewCounts(Integer.valueOf(0));
			this.newsService.save(this.news);
		} else {
			old = this.newsService.get(this.news.getNewsId());
			this.news.setUpdateTime(new Date());
			this.news.setCreatetime(old.getCreatetime());
			this.news.setViewCounts(Integer.valueOf(old.getViewCounts()
					.intValue() + 1));
			this.news.setReplyCounts(old.getReplyCounts());
			this.newsService.merge(this.news);
		}
		this.setJsonString("{success:true}");
		return "success";
	}

	public String category() {
		List<News> list = null;
		PagingBean pb = this.getInitPagingBean();
		if ((this.typeId != null) && (this.typeId.longValue() > 0L)) {
			list = this.newsService.findByTypeId(this.typeId, pb);
		} else {
			list = this.newsService.getAll(pb);
		}
		Type type = new TypeToken<List<News>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':"
				+ pb.getTotalItems() + ",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String icon() {
		this.setNews(this.newsService.get(this.getNewsId()));
		this.news.setSubjectIcon("");
		this.newsService.save(this.news);
		return "success";
	}

	public String search() {
		PagingBean pb = this.getInitPagingBean();
		String searchContent = this.getRequest().getParameter("searchContent");
		List<News> list = this.newsService.findBySearch(searchContent, pb);
		Type type = new TypeToken<News[]>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String display() {
		PagingBean pb = new PagingBean(0, 8);

		List<News> list = this.newsService.findBySearch(null, pb);
		this.getRequest().setAttribute("newsList", list);
		return "display";
	}

	public String image() {
		PagingBean pb = new PagingBean(0, 8);

		List<News> list = this.newsService.findImageNews(pb);
		List<News> newList = new ArrayList<News>();
		for (News news : list) {
			String content = StringUtil.html2Text(news.getContent());
			if (content.length() > 250) {
				content = content.substring(0, 250);
			}
			news.setContent(content);
			newList.add(news);
		}
		this.getRequest().setAttribute("imageNewsList", newList);
		return "image";
	}
}
