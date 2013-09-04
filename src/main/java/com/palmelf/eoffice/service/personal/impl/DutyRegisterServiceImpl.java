package com.palmelf.eoffice.service.personal.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.util.DateUtil;
import com.palmelf.eoffice.dao.personal.DutyRegisterDao;
import com.palmelf.eoffice.dao.personal.DutySectionDao;
import com.palmelf.eoffice.model.personal.DutyRegister;
import com.palmelf.eoffice.model.personal.DutySection;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.personal.DutyRegisterService;

import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;

public class DutyRegisterServiceImpl extends BaseServiceImpl<DutyRegister>
		implements DutyRegisterService {
	private DutyRegisterDao dao;

	@Resource
	private DutySectionDao dutySectionDao;

	public DutyRegisterServiceImpl(DutyRegisterDao dao) {
		super(dao);
		this.dao = dao;
	}

	public void signInOff(Long sectionId, Short signInOff, AppUser curUser,
			Date registerDate) {
		DutySection dutySection = this.dutySectionDao.get(sectionId);

		DutyRegister dutyRegister = this.dao.getTodayUserRegister(
				curUser.getUserId(), signInOff, sectionId);
		if (dutyRegister != null) {
			return;
		}

		DutyRegister register = new DutyRegister();
		register.setAppUser(curUser);
		register.setFullname(curUser.getFullname());

		Calendar regiserCal = Calendar.getInstance();
		regiserCal.setTime(registerDate);
		register.setRegisterDate(registerDate);
		register.setDayOfWeek(Integer.valueOf(regiserCal.get(7)));
		register.setInOffFlag(signInOff);

		register.setDutySection(dutySection);

		Calendar startCalendar = Calendar.getInstance();
		if (DutyRegister.SIGN_IN.equals(signInOff))
			startCalendar.setTime(dutySection.getDutyStartTime());
		else {
			startCalendar.setTime(dutySection.getDutyEndTime());
		}

		DateUtil.copyYearMonthDay(startCalendar, regiserCal);

		if (DutyRegister.SIGN_IN.equals(signInOff)) {
			if (regiserCal.compareTo(startCalendar) > 0) {
				register.setRegFlag(DutyRegister.REG_FLAG_LATE);

				Long minis = Long
						.valueOf((regiserCal.getTimeInMillis() - startCalendar
								.getTimeInMillis()) / 60000L);
				register.setRegMins(Integer.valueOf(minis.intValue()));
			} else {
				register.setRegFlag(DutyRegister.REG_FLAG_NORMAL);
				register.setRegMins(Integer.valueOf(0));
			}
		} else if (regiserCal.compareTo(startCalendar) < 0) {
			register.setRegFlag(DutyRegister.REG_FLAG_EARLY_OFF);

			Long minis = Long
					.valueOf((startCalendar.getTimeInMillis() - regiserCal
							.getTimeInMillis()) / 60000L);
			register.setRegMins(Integer.valueOf(minis.intValue()));
		} else {
			register.setRegFlag(DutyRegister.REG_FLAG_NORMAL);
			register.setRegMins(Integer.valueOf(0));
		}

		save(register);
	}

	public DutyRegister getTodayUserRegister(Long userId, Short inOffFlag,
			Long sectionId) {
		return this.dao.getTodayUserRegister(userId, inOffFlag, sectionId);
	}
}
