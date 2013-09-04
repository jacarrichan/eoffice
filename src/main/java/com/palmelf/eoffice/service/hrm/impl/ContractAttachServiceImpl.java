package com.palmelf.eoffice.service.hrm.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.hrm.ContractAttachDao;
import com.palmelf.eoffice.model.hrm.ContractAttach;
import com.palmelf.eoffice.service.hrm.ContractAttachService;

public class ContractAttachServiceImpl extends BaseServiceImpl<ContractAttach>
		implements ContractAttachService {
	private ContractAttachDao dao;

	public ContractAttachServiceImpl(ContractAttachDao dao) {
		super(dao);
		this.dao = dao;
	}
}
