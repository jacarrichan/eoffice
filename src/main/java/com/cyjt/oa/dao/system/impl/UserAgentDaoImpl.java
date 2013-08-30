package com.cyjt.oa.dao.system.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.UserAgentDao;
import com.cyjt.oa.model.system.UserAgent;
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
