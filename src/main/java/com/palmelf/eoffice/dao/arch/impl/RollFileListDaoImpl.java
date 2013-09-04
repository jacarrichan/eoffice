package com.palmelf.eoffice.dao.arch.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.arch.RollFileListDao;
import com.palmelf.eoffice.model.arch.RollFileList;

public class RollFileListDaoImpl extends BaseDaoImpl<RollFileList> implements
		RollFileListDao {
	public RollFileListDaoImpl() {
		super(RollFileList.class);
	}
}
