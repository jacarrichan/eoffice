package com.cyjt.oa.dao.flow.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.flow.ProUserAssignDao;
import com.cyjt.oa.model.flow.ProUserAssign;
import java.util.List;

public class ProUserAssignDaoImpl extends BaseDaoImpl<ProUserAssign> implements
		ProUserAssignDao {
	public ProUserAssignDaoImpl() {
		super(ProUserAssign.class);
	}

	public List<ProUserAssign> getByDeployId(String deployId) {
		String hql = "from ProUserAssign pua where pua.deployId=?";
		return findByHql(hql, new Object[] { deployId });
	}

	public ProUserAssign getByDeployIdActivityName(String deployId,
			String activityName) {
		String hql = "from ProUserAssign pua where pua.deployId=? and pua.activityName=?";
		return (ProUserAssign) findUnique(hql, new Object[] { deployId,
				activityName });
	}
}
