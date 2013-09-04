package com.palmelf.eoffice.dao.personal.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.util.DateUtil;
import com.palmelf.eoffice.dao.personal.DutyRegisterDao;
import com.palmelf.eoffice.model.personal.DutyRegister;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DutyRegisterDaoImpl extends BaseDaoImpl<DutyRegister> implements
		DutyRegisterDao {
	public DutyRegisterDaoImpl() {
		super(DutyRegister.class);
	}

	public DutyRegister getTodayUserRegister(Long userId, Short inOffFlag,
			Long sectionId) {
		String hql = "from DutyRegister dr where dr.appUser.userId=? and dr.registerDate>=? and dr.registerDate<=? and dr.inOffFlag=? and dr.dutySection.sectionId=?";
		Calendar cal = Calendar.getInstance();
		Date startTime = DateUtil.setStartDay(cal).getTime();
		Date endTime = DateUtil.setEndDay(cal).getTime();
		List list = findByHql(hql, new Object[] { userId, startTime, endTime,
				inOffFlag, sectionId });

		if (list.size() > 0) {
			return (DutyRegister) list.get(0);
		}

		return null;
	}
}
