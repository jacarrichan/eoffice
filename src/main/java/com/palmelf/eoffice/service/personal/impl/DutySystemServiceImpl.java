package com.palmelf.eoffice.service.personal.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.personal.DutySystemDao;
import com.palmelf.eoffice.model.personal.DutySystem;
import com.palmelf.eoffice.service.personal.DutySystemService;

import java.util.List;

public class DutySystemServiceImpl extends BaseServiceImpl<DutySystem>
		implements DutySystemService {
	private DutySystemDao dao;

	public DutySystemServiceImpl(DutySystemDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public DutySystem save(DutySystem duty) {
		if (DutySystem.DEFAULT.equals(duty.getIsDefault())) {
			this.dao.updateForNotDefult();
		}
		this.dao.save(duty);
		return duty;
	}

	public DutySystem getDefaultDutySystem() {
		List list = this.dao.getDefaultDutySystem();
		if (list.size() > 0) {
			return (DutySystem) list.get(0);
		}
		return null;
	}
}
