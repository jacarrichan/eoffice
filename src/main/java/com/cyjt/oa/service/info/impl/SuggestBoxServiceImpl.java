package com.cyjt.oa.service.info.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.info.SuggestBoxDao;
import com.cyjt.oa.model.info.SuggestBox;
import com.cyjt.oa.service.info.SuggestBoxService;

public class SuggestBoxServiceImpl extends BaseServiceImpl<SuggestBox>
		implements SuggestBoxService {
	private SuggestBoxDao dao;

	public SuggestBoxServiceImpl(SuggestBoxDao dao) {
		super(dao);
		this.dao = dao;
	}
}
