package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.info.SuggestBoxDao;
import com.cyjt.oa.model.info.SuggestBox;

public class SuggestBoxDaoImpl extends BaseDaoImpl<SuggestBox> implements
		SuggestBoxDao {
	public SuggestBoxDaoImpl() {
		super(SuggestBox.class);
	}
}
