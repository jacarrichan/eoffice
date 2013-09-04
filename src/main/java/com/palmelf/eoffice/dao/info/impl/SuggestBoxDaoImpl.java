package com.palmelf.eoffice.dao.info.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.info.SuggestBoxDao;
import com.palmelf.eoffice.model.info.SuggestBox;

public class SuggestBoxDaoImpl extends BaseDaoImpl<SuggestBox> implements
		SuggestBoxDao {
	public SuggestBoxDaoImpl() {
		super(SuggestBox.class);
	}
}
