package com.cyjt.oa.service.admin.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.admin.CarDao;
import com.cyjt.oa.model.admin.Car;
import com.cyjt.oa.service.admin.CarService;

public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService {
	private CarDao dao;

	public CarServiceImpl(CarDao dao) {
		super(dao);
		this.dao = dao;
	}
}
