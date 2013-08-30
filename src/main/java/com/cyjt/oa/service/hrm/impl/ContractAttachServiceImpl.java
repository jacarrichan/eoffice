package com.cyjt.oa.service.hrm.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.hrm.ContractAttachDao;
import com.cyjt.oa.model.hrm.ContractAttach;
import com.cyjt.oa.service.hrm.ContractAttachService;

public class ContractAttachServiceImpl extends BaseServiceImpl<ContractAttach>
		implements ContractAttachService {
	private ContractAttachDao dao;

	public ContractAttachServiceImpl(ContractAttachDao dao) {
		super(dao);
		this.dao = dao;
	}
}
