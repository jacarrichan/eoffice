package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.flow.ProcessRun;

import java.util.List;

public abstract interface ProcessRunDao extends BaseDao<ProcessRun> {
	public abstract ProcessRun getByPiId(String paramString);

	public abstract List<ProcessRun> getByDefId(Long paramLong,
			PagingBean paramPagingBean);

	public abstract List<ProcessRun> getByUserIdSubject(Long paramLong,
			String paramString, PagingBean paramPagingBean);
}
