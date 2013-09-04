package com.palmelf.eoffice.dao.info;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.Notice;

import java.util.Date;
import java.util.List;

public abstract interface NoticeDao extends BaseDao<Notice> {
	public abstract List<Notice> findByNoticeId(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<Notice> findBySearch(Notice paramNotice,
			Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

	public abstract List<Notice> findBySearch(String paramString,
			PagingBean paramPagingBean);
}
