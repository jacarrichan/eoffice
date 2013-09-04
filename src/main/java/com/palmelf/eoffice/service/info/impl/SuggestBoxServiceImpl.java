package com.palmelf.eoffice.service.info.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.info.SuggestBoxDao;
import com.palmelf.eoffice.model.info.SuggestBox;
import com.palmelf.eoffice.service.info.SuggestBoxService;

public class SuggestBoxServiceImpl extends BaseServiceImpl<SuggestBox>
		implements SuggestBoxService {
	private SuggestBoxDao dao;

	public SuggestBoxServiceImpl(SuggestBoxDao dao) {
		super(dao);
		this.dao = dao;
	}
}
