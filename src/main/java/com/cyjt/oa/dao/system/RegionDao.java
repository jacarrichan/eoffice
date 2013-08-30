package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.Region;
import java.util.List;

public abstract interface RegionDao extends BaseDao<Region> {
	public abstract List<Region> getProvince();

	public abstract List<Region> getCity(Long paramLong);
}
