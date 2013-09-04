package com.palmelf.eoffice.dao.archive.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.archive.ArchTemplateDao;
import com.palmelf.eoffice.model.archive.ArchTemplate;

public class ArchTemplateDaoImpl extends BaseDaoImpl<ArchTemplate> implements
		ArchTemplateDao {
	public ArchTemplateDaoImpl() {
		super(ArchTemplate.class);
	}
}
