package com.cyjt.oa.dao.archive.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.archive.ArchTemplateDao;
import com.cyjt.oa.model.archive.ArchTemplate;

public class ArchTemplateDaoImpl extends BaseDaoImpl<ArchTemplate> implements
		ArchTemplateDao {
	public ArchTemplateDaoImpl() {
		super(ArchTemplate.class);
	}
}
