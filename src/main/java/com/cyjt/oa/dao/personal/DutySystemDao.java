package com.cyjt.oa.dao.personal;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.personal.DutySystem;
import java.util.List;

public abstract interface DutySystemDao extends BaseDao<DutySystem> {
	public abstract void updateForNotDefult();

	public abstract List<DutySystem> getDefaultDutySystem();
}
