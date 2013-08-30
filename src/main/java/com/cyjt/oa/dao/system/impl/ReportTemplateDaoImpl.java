package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.ReportTemplateDao;
import com.cyjt.oa.model.system.ReportTemplate;

public class ReportTemplateDaoImpl extends BaseDaoImpl<ReportTemplate>
		implements ReportTemplateDao {
	public ReportTemplateDaoImpl() {
		super(ReportTemplate.class);
	}
}
