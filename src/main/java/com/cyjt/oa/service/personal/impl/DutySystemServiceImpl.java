package com.cyjt.oa.service.personal.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.personal.DutySystemDao;
import com.cyjt.oa.model.personal.DutySystem;
import com.cyjt.oa.service.personal.DutySystemService;
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
