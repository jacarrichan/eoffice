package com.palmelf.eoffice.service.system.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.system.UserSubDao;
import com.palmelf.eoffice.model.system.UserSub;
import com.palmelf.eoffice.service.system.UserSubService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UserSubServiceImpl extends BaseServiceImpl<UserSub> implements
		UserSubService {
	private UserSubDao dao;

	public UserSubServiceImpl(UserSubDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Set<Long> findAllUpUser(Long userId) {
		List<Long> list = this.dao.upUser(userId);
		Set set = new HashSet();
		for (Long l : list) {
			set.add(l);
			List<Long> newlist = this.dao.upUser(l);
			Set sets = new HashSet();
			for (Long lon : newlist) {
				set.add(lon);
				sets.add(lon);
			}
			findUp(set, sets);
		}
		return set;
	}

	public void findUp(Set<Long> setOld, Set<Long> setNew) {
		Iterator it = setNew.iterator();
		while (it.hasNext()) {
			Long userId = (Long) it.next();
			List<Long> newlist = this.dao.upUser(userId);
			setOld.add(userId);
			Set set = new HashSet();
			for (Long lon : newlist) {
				if (!setOld.contains(lon)) {
					set.add(lon);
				}
			}
			findUp(setOld, set);
		}
	}

	public List<Long> subUsers(Long userId) {
		return this.dao.subUsers(userId);
	}

	public List<Long> upUser(Long userId) {
		return this.dao.upUser(userId);
	}

	public List<UserSub> findByUser(Long userId) {
		return this.dao.findByUser(userId);
	}

	public void delSubUser(Long userId) {
		List<UserSub> userList = findByUser(userId);
		for (UserSub sub : userList)
			this.dao.remove(sub);
	}
}
