package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.ReportTemplateDao;
import com.cyjt.oa.model.system.ReportTemplate;
import com.cyjt.oa.service.system.ReportTemplateService;

public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate>
		implements ReportTemplateService {
	private ReportTemplateDao dao;

	public ReportTemplateServiceImpl(ReportTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}
}
