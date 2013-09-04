package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.UserAgentDao;
import com.palmelf.eoffice.model.system.UserAgent;
import com.palmelf.eoffice.service.system.UserAgentService;

import java.util.List;

public class UserAgentServiceImpl extends BaseServiceImpl<UserAgent> implements
		UserAgentService {
	private UserAgentDao dao;

	public UserAgentServiceImpl(UserAgentDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<UserAgent> getByUserId(Long userId) {
		return this.dao.getByUserId(userId);
	}

	public void delUserGrants(Long userId) {
		List<UserAgent> list = getByUserId(userId);
		for (UserAgent userAgent : list)
			this.dao.remove(userAgent);
	}

	public UserAgent getByUserIdGrantId(Long userId, Long grantUId) {
		return this.dao.getByUserIdGrantId(userId, grantUId);
	}
}
