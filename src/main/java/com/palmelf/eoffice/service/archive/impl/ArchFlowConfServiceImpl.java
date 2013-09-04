package com.palmelf.eoffice.service.archive.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.archive.ArchFlowConfDao;
import com.palmelf.eoffice.model.archive.ArchFlowConf;
import com.palmelf.eoffice.service.archive.ArchFlowConfService;

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
