package com.palmelf.test.task;

import com.palmelf.eoffice.dao.task.AppointmentDao;
import com.palmelf.eoffice.model.task.Appointment;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class AppointmentDaoTestCase extends BaseTestCase {

	@Resource
	private AppointmentDao appointmentDao;

	@Test
	@Rollback(false)
	public void add() {
		Appointment appointment = new Appointment();
		this.appointmentDao.save(appointment);
	}
}
