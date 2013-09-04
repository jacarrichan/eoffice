package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.RegionDao;
import com.palmelf.eoffice.model.system.Region;
import com.palmelf.eoffice.service.system.RegionService;

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
