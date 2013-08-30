package com.cyjt.oa.service.communicate.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.communicate.PhoneGroupDao;
import com.cyjt.oa.model.communicate.PhoneGroup;
import com.cyjt.oa.service.communicate.PhoneGroupService;
import java.util.List;

public class PhoneGroupServiceImpl extends BaseServiceImpl<PhoneGroup>
		implements PhoneGroupService {
	private PhoneGroupDao dao;

	public PhoneGroupServiceImpl(PhoneGroupDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Integer findLastSn(Long userId) {
		return this.dao.findLastSn(userId);
	}

	public PhoneGroup findBySn(Integer sn, Long userId) {
		return this.dao.findBySn(sn, userId);
	}

	public List<PhoneGroup> findBySnUp(Integer sn, Long userId) {
		return this.dao.findBySnUp(sn, userId);
	}

	public List<PhoneGroup> findBySnDown(Integer sn, Long userId) {
		return this.dao.findBySnDown(sn, userId);
	}

	public List<PhoneGroup> getAll(Long userId) {
		return this.dao.getAll(userId);
	}
}
