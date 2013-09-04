package com.palmelf.eoffice.dao.personal;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.personal.DutySystem;

import java.util.List;

public abstract interface DutySystemDao extends BaseDao<DutySystem> {
	public abstract void updateForNotDefult();

	public abstract List<DutySystem> getDefaultDutySystem();
}
