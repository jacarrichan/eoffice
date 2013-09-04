package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.Region;

import java.util.List;

public abstract interface RegionDao extends BaseDao<Region> {
	public abstract List<Region> getProvince();

	public abstract List<Region> getCity(Long paramLong);
}
