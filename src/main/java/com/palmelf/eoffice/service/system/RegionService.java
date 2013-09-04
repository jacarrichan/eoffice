package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.Region;

import java.util.List;

public abstract interface RegionService extends BaseService<Region> {
	public abstract List<Region> getProvince();

	public abstract List<Region> getCity(Long paramLong);
}
