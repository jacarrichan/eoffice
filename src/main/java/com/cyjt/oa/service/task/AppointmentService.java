package com.cyjt.oa.service.task;

import com.cyjt.core.service.BaseService;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.task.Appointment;
import java.util.List;

public abstract interface AppointmentService extends BaseService<Appointment> {
	public abstract List showAppointmentByUserId(PagingBean paramPagingBean);

	public abstract void warningAppointment();
}
