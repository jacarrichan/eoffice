package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.RegionDao;
import com.cyjt.oa.model.system.Region;
import java.util.List;

public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {
	public RegionDaoImpl() {
		super(Region.class);
	}

	public List<Region> getProvince() {
		Long parentId = Long.valueOf(0L);
		String hql = "from Region r where r.parentId = ?";
		return findByHql(hql, new Object[] { parentId });
	}

	public List<Region> getCity(Long regionId) {
		String hql = "from Region r where r.parentId = ?";
		return findByHql(hql, new Object[] { regionId });
	}
}
