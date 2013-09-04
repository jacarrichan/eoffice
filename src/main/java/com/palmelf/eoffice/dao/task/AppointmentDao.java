package com.palmelf.eoffice.dao.task;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.task.Appointment;

import java.util.List;

public abstract interface AppointmentDao extends BaseDao<Appointment> {
	public abstract List showAppointmentByUserId(PagingBean paramPagingBean);

	public abstract List<Appointment> warningAppointment();
}
