package com.cyjt.oa.dao.arch.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.arch.RollFileListDao;
import com.cyjt.oa.model.arch.RollFileList;

public class RollFileListDaoImpl extends BaseDaoImpl<RollFileList> implements
		RollFileListDao {
	public RollFileListDaoImpl() {
		super(RollFileList.class);
	}
}
