package com.palmelf.eoffice.dao.task.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.task.AppointmentDao;
import com.palmelf.eoffice.model.task.Appointment;

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
