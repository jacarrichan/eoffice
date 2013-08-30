package com.cyjt.oa.dao.arch.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.arch.RollFileDao;
import com.cyjt.oa.model.arch.RollFile;

public class RollFileDaoImpl extends BaseDaoImpl<RollFile> implements
		RollFileDao {
	public RollFileDaoImpl() {
		super(RollFile.class);
	}
}
