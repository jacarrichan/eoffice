package com.cyjt.oa.service.archive;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfService extends BaseService<ArchFlowConf> {
	public abstract ArchFlowConf getByFlowType(Short paramShort);
}
