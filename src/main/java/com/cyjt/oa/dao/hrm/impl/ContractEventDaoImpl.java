package com.cyjt.oa.dao.hrm.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.ContractEventDao;
import com.cyjt.oa.model.hrm.ContractEvent;
import java.util.List;

public class ContractEventDaoImpl extends BaseDaoImpl<ContractEvent> implements
		ContractEventDao {
	public ContractEventDaoImpl() {
		super(ContractEvent.class);
	}

	public List<ContractEvent> findByContractId(Long contractId) {
		String hql = "from ContractEvent ce where ce.userContract.contractId = ?";
		return findByHql(hql, new Object[] { contractId });
	}
}
