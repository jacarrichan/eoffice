package com.palmelf.eoffice.service.document.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.document.PaintTemplateDao;
import com.palmelf.eoffice.model.document.PaintTemplate;
import com.palmelf.eoffice.service.document.PaintTemplateService;

public class PaintTemplateServiceImpl extends BaseServiceImpl<PaintTemplate>
		implements PaintTemplateService {
	private PaintTemplateDao dao;

	public PaintTemplateServiceImpl(PaintTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}
}
