package com.cyjt.oa.dao.archive;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfDao extends BaseDao<ArchFlowConf> {
	public abstract ArchFlowConf getByFlowType(Short paramShort);
}
