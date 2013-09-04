package com.palmelf.eoffice.action.info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.info.News;
import com.palmelf.eoffice.model.info.NewsComment;
import com.palmelf.eoffice.model.system.SysConfig;
import com.palmelf.eoffice.service.info.NewsCommentService;
import com.palmelf.eoffice.service.info.NewsService;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.SysConfigService;

public class NewsCommentAction extends BaseAction {

	@Resource
	private NewsCommentService newsCommentService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private NewsService newsService;

	@Resource
	private SysConfigService sysConfigSerivce;
	private NewsComment newsComment;
	private Long commentId;

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public NewsComment getNewsComment() {
		return this.newsComment;
	}

	public void setNewsComment(NewsComment newsComment) {
		this.newsComment = newsComment;
	}

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(this.getRequest());
		String start = this.getRequest().getParameter("start");
		String newId = this.getRequest().getParameter("Q_news.newsId_L_EQ");
		SysConfig commentConfig = this.sysConfigSerivce
				.findByKey("commentConfig");
		if ((newId != null) && (commentConfig.getDataValue().equals("1"))) {
			filter.addFilter("Q_status_SN_EQ", "1");
		}

		List<NewsComment> list = this.newsCommentService.getAll(filter);

		Gson gson = new Gson();
		SimpleDateFormat simpleDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		for (NewsComment newsComment : list) {
			buff.append("{commentId:'").append(newsComment.getCommentId())
					.append("',subject:")
					.append(gson.toJson(newsComment.getNews().getSubject()))
					.append(",content:")
					.append(gson.toJson(newsComment.getContent()))
					.append(",createtime:'")
					.append(simpleDate.format(newsComment.getCreatetime()))
					.append("',fullname:'").append(newsComment.getFullname())
					.append("',start:'").append(start).append("',status:'")
					.append(newsComment.getStatus()).append("'},");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				NewsComment delComment = this.newsCommentService.get(new Long(
						id));
				News news = delComment.getNews();
				if (news.getReplyCounts().intValue() > 1) {
					news.setReplyCounts(Integer.valueOf(news.getReplyCounts()
							.intValue() - 1));
				}
				this.newsService.save(news);
				this.newsCommentService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		NewsComment newsComment = this.newsCommentService.get(this.commentId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(newsComment));
		sb.append("}");
		this.setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		News news = this.newsService.get(this.newsComment.getNewsId());
		news.setReplyCounts(Integer
				.valueOf(news.getReplyCounts().intValue() + 1));
		this.newsService.save(news);

		this.newsComment.setAppUser(this.appUserService.get(this.newsComment
				.getUserId()));
		this.newsComment.setCreatetime(new Date());
		this.newsComment.setNews(news);

		this.newsComment.setStatus(NewsComment.STATUS_NONE);

		this.newsCommentService.save(this.newsComment);
		this.setJsonString("{success:true}");
		return "success";
	}

	public String audit() {
		this.newsComment = (this.newsCommentService.get(this.commentId));
		this.newsComment.setStatus(NewsComment.STATUS_PASS);

		this.newsCommentService.save(this.newsComment);
		this.setJsonString("{success:true}");
		return "success";
	}
}
