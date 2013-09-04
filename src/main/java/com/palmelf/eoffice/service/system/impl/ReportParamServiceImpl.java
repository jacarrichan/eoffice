package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.ReportParamDao;
import com.palmelf.eoffice.model.system.ReportParam;
import com.palmelf.eoffice.service.system.ReportParamService;

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
