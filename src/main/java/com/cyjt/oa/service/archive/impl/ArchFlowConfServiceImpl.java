package com.cyjt.oa.service.archive.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.archive.ArchFlowConfDao;
import com.cyjt.oa.model.archive.ArchFlowConf;
import com.cyjt.oa.service.archive.ArchFlowConfService;

public class ArchFlowConfServiceImpl extends BaseServiceImpl<ArchFlowConf>
		implements ArchFlowConfService {
	private ArchFlowConfDao dao;

	public ArchFlowConfServiceImpl(ArchFlowConfDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ArchFlowConf getByFlowType(Short archType) {
		return this.dao.getByFlowType(archType);
	}
}
