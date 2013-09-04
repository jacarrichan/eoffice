package com.palmelf.eoffice.dao.document.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.document.PaintTemplateDao;
import com.palmelf.eoffice.model.document.PaintTemplate;

public class PaintTemplateDaoImpl extends BaseDaoImpl<PaintTemplate> implements
		PaintTemplateDao {
	public PaintTemplateDaoImpl() {
		super(PaintTemplate.class);
	}
}
