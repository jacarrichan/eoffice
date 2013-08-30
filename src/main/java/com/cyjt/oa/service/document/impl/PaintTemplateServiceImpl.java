package com.cyjt.oa.service.document.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.document.PaintTemplateDao;
import com.cyjt.oa.model.document.PaintTemplate;
import com.cyjt.oa.service.document.PaintTemplateService;

public class PaintTemplateServiceImpl extends BaseServiceImpl<PaintTemplate>
		implements PaintTemplateService {
	private PaintTemplateDao dao;

	public PaintTemplateServiceImpl(PaintTemplateDao dao) {
		super(dao);
		this.dao = dao;
	}
}
