package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.ReportTemplateDao;
import com.palmelf.eoffice.model.system.ReportTemplate;

public class ReportTemplateDaoImpl extends BaseDaoImpl<ReportTemplate>
		implements ReportTemplateDao {
	public ReportTemplateDaoImpl() {
		super(ReportTemplate.class);
	}
}
