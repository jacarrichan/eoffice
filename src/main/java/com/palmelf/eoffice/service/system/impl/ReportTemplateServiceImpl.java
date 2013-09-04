package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.ReportTemplateDao;
import com.palmelf.eoffice.model.system.ReportTemplate;
import com.palmelf.eoffice.service.system.ReportTemplateService;

public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate>
		implements ReportTemplateService {
	private ReportTemplateDao dao;

	public ReportTemplateServiceImpl(ReportTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}
}
