package com.palmelf.eoffice.dao.arch.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.arch.RollFileDao;
import com.palmelf.eoffice.model.arch.RollFile;

public class RollFileDaoImpl extends BaseDaoImpl<RollFile> implements
		RollFileDao {
	public RollFileDaoImpl() {
		super(RollFile.class);
	}
}
