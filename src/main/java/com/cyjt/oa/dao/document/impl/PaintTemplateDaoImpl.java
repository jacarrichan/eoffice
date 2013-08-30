package com.cyjt.oa.dao.document.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.document.PaintTemplateDao;
import com.cyjt.oa.model.document.PaintTemplate;

public class PaintTemplateDaoImpl extends BaseDaoImpl<PaintTemplate> implements
		PaintTemplateDao {
	public PaintTemplateDaoImpl() {
		super(PaintTemplate.class);
	}
}
