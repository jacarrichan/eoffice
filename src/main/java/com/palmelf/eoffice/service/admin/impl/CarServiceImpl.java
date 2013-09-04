package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.admin.CarDao;
import com.palmelf.eoffice.model.admin.Car;
import com.palmelf.eoffice.service.admin.CarService;

public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService {
	private CarDao dao;

	public CarServiceImpl(CarDao dao) {
		super(dao);
		this.dao = dao;
	}
}
