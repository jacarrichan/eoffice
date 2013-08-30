package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.Region;
import java.util.List;

public abstract interface RegionService extends BaseService<Region> {
	public abstract List<Region> getProvince();

	public abstract List<Region> getCity(Long paramLong);
}
