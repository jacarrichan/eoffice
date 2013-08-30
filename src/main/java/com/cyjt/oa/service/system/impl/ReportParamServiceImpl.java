package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.ReportParamDao;
import com.cyjt.oa.model.system.ReportParam;
import com.cyjt.oa.service.system.ReportParamService;
import java.util.List;

public class ReportParamServiceImpl extends BaseServiceImpl<ReportParam>
		implements ReportParamService {
	private ReportParamDao dao;

	public ReportParamServiceImpl(ReportParamDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<ReportParam> findByRepTemp(Long reportId) {
		return this.dao.findByRepTemp(reportId);
	}
}
