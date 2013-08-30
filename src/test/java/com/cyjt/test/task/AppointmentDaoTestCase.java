package com.cyjt.test.task;

import com.cyjt.oa.dao.task.AppointmentDao;
import com.cyjt.oa.model.task.Appointment;
import com.cyjt.test.BaseTestCase;
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
