package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.Constants;
import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.NoticeDao;
import com.cyjt.oa.model.info.Notice;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao {
	public NoticeDaoImpl() {
		super(Notice.class);
	}

	public List<Notice> findBySearch(Notice notice, Date from, Date to,
			PagingBean pb) {
		StringBuffer hql = new StringBuffer("from Notice notice where 1=1");
		List params = new ArrayList();
		if ((!"".equals(notice.getPostName()))
				&& (notice.getPostName() != null)) {
			hql.append("and notice.postName like ?");
			params.add("%" + notice.getPostName() + "%");
		}
		if ((!"".equals(notice.getNoticeTitle()))
				&& (notice.getNoticeTitle() != null)) {
			hql.append("and notice.noticeTitle like ?");
			params.add("%" + notice.getNoticeTitle() + "%");
		}
		if (from != null) {
			hql.append("and notice.effectivDate > ?");
			params.add(from);
		}
		if (to != null) {
			hql.append("and notice.expirationDate < ?");
			params.add(to);
		}
		return findByHql(hql.toString(), params.toArray(), pb);
	}

	public List<Notice> findByNoticeId(Long noticeId, PagingBean pb) {
		String hql = "from Notice notice where notice.noticeId=?";
		Object[] params = { noticeId };
		return findByHql("from Notice notice where notice.noticeId=?", params,
				pb);
	}

	public List<Notice> findBySearch(String searchContent, PagingBean pb) {
		ArrayList params = new ArrayList();
		StringBuffer hql = new StringBuffer("from Notice nt where nt.state = ?");
		params.add(Constants.FLAG_ACTIVATION);
		if (StringUtils.isNotEmpty(searchContent)) {
			hql.append(" and (nt.noticeTitle like ? or nt.noticeContent like ?)");
			params.add("%" + searchContent + "%");
			params.add("%" + searchContent + "%");
		}
		hql.append(" order by nt.noticeId desc");
		return findByHql(hql.toString(), params.toArray(), pb);
	}
}
