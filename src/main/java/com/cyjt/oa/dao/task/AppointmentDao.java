package com.cyjt.oa.dao.task;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.task.Appointment;
import java.util.List;

public abstract interface AppointmentDao extends BaseDao<Appointment> {
	public abstract List showAppointmentByUserId(PagingBean paramPagingBean);

	public abstract List<Appointment> warningAppointment();
}
