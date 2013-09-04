package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.UserAgentDao;
import com.palmelf.eoffice.model.system.UserAgent;

import java.util.List;

public class UserAgentDaoImpl extends BaseDaoImpl<UserAgent> implements
		UserAgentDao {
	public UserAgentDaoImpl() {
		super(UserAgent.class);
	}

	public List<UserAgent> getByUserId(Long userId) {
		String hql = "from UserAgent ua where ua.userId=?";
		return findByHql(hql, new Object[] { userId });
	}

	public UserAgent getByUserIdGrantId(Long userId, Long grantUId) {
		String hql = "from UserAgent ua where ua where ua.userId=? and ua.grantUId=?";
		return (UserAgent) findUnique(hql, new Object[] { userId, grantUId });
	}
}
