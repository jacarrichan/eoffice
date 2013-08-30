package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.RegionDao;
import com.cyjt.oa.model.system.Region;
import com.cyjt.oa.service.system.RegionService;
import java.util.List;

public class RegionServiceImpl extends BaseServiceImpl<Region> implements
		RegionService {
	private RegionDao dao;

	public RegionServiceImpl(RegionDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Region> getProvince() {
		return this.dao.getProvince();
	}

	public List<Region> getCity(Long regionId) {
		return this.dao.getCity(regionId);
	}
}
