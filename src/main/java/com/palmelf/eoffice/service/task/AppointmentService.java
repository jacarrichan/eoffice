package com.palmelf.eoffice.service.task;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.task.Appointment;

import java.util.List;

public abstract interface AppointmentService extends BaseService<Appointment> {
	public abstract List showAppointmentByUserId(PagingBean paramPagingBean);

	public abstract void warningAppointment();
}
