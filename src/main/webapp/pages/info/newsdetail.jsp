<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.cyjt.core.util.AppUtil"%>
<%@page import="com.cyjt.oa.service.info.NewsService"%>
<%@page import="com.cyjt.oa.service.info.impl.NewsServiceImpl"%>
<%@page import="com.cyjt.core.web.paging.PagingBean"%>
<%@page import="com.cyjt.oa.model.info.News"%>
<%@page import="com.cyjt.oa.model.info.NewsType"%>
<%@page import="com.cyjt.oa.service.info.NewsTypeService"%>
<%@page import="com.cyjt.core.command.QueryFilter"%>
<%@page import="java.util.List"%>
<%
	Long newsId = null;
	String strId = request.getParameter("newsId");
	String opt = request.getParameter("opt");
	NewsService newsService = (NewsService) AppUtil.getBean("newsService");
	//通过id得到News
	News news = null;
	if (strId != null && !"".equals(strId)) {
		newsId = new Long(strId);
	}
	
	//使用页面的方法实现获取上一条,下一条的记录
	
	if(opt != null && !"".equals(opt)){
		QueryFilter filter=new QueryFilter(request);
		filter.getPagingBean().setPageSize(1);//只取一条记录
		List<News> list = null;
		filter.addFilter("Q_status_SN_EQ","1");//只取生效的新闻
		if(opt.equals("_next")){
			//点击下一条按钮,则取比当前ID大的下一条记录
			filter.addFilter("Q_newsId_L_GT", newsId.toString());
			list= newsService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextNewsFlag","endNext");
			}
		}else if(opt.equals("_pre")){
			//点击上一条按钮,则取比当前ID小的下一条记录
			filter.addFilter("Q_newsId_L_LT", newsId.toString());
			filter.addSorted("newsId","desc");
			list= newsService.getAll(filter);
			if(filter.getPagingBean().getStart()+1==filter.getPagingBean().getTotalItems()){
				request.setAttribute("__haveNextNewsFlag","endPre");
			}
		}
		if(list.size()>0){
			news = list.get(0);
		}else{
			news = newsService.get(newsId);
		}
	}else{
		news = newsService.get(newsId);
		request.setAttribute("__haveNextNewsFlag","");
	}
	
	request.setAttribute("news",news);
	request.setAttribute("ctxPath",request.getContextPath());
	//浏览后的新闻浏览次数加1
	news.setViewCounts(news.getViewCounts()+1);
	newsService.save(news);
%>

<table width="98%" cellpadding="0" cellspacing="1" style="border: 5px 5px 5px 5px;">
	<tr>
		<td align="center" style="font:2.0em 宋体  ;color:black;font-weight: bold;padding:10px 0px 10px 0px; ">
			${news.subject }
			<input type="hidden" value="${news.newsId }" id="__curNewsId"/>
			<input type="hidden" value="${__haveNextNewsFlag}" id="__haveNextNewsFlag"/>
		</td>
	</tr>
	<tr>
		<td align="center" style="padding:0px 0px 10px 0px;">
			<font color="red">
				<fmt:formatDate value="${news.createtime}" pattern="yyyy-MM-dd HH:mm"/>
			</font>
			&nbsp;&nbsp;新闻类型:
			<font color="green">
				${news.newsType.typeName}
			</font>
			&nbsp;&nbsp;浏览次数:
			<font color="red">
				${news.viewCounts }
			</font>
			&nbsp;&nbsp;回复次数:
			<font color="red">
				${news.replyCounts}
			</font>
		</td>
	</tr>
	
	<tr>
		<td style="border-top:dashed 1px #ccc;" height="28">
			
		</td>
	</tr>
	<c:if test="${news.isDeskImage==1}">
	<tr>
		<td align="center">
			<img src="${ctxPath}/attachFiles/${news.subjectIcon}"/>
		</td>
	</tr>
	</c:if>
	<tr >
		<td style="font:13px 宋体;color: black;line-height:24px;">
			${news.content}
		</td>
	</tr>
	<tr>
		<td align="right">
				(作者:
			<font color="green">
				${news.author}
			</font>
				&nbsp;&nbsp;发布人:
			<font color="green">
				${news.issuer}
			</font>
				 )
		</td>
	</tr>
	
</table>
