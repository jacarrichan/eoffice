package com.cyjt.oa.dao.info;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.info.Notice;
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
