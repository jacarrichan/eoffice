package com.cyjt.oa.dao.task.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.task.AppointmentDao;
import com.cyjt.oa.model.task.Appointment;
import java.util.List;

public class AppointmentDaoImpl extends BaseDaoImpl<Appointment> implements
		AppointmentDao {
	public AppointmentDaoImpl() {
		super(Appointment.class);
	}

	public List showAppointmentByUserId(PagingBean pb) {
		StringBuffer hql = new StringBuffer(
				"select vo from Appointment vo order by vo.appointId desc");
		return findByHql(hql.toString(), null, pb);
	}

	public List<Appointment> warningAppointment() {
		String hql = "select vo from Appointment vo order by vo.startTime desc";
		return findByHql(hql);
	}
}
