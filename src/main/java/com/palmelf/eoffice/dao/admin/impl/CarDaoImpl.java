package com.palmelf.eoffice.dao.admin.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.admin.CarDao;
import com.palmelf.eoffice.model.admin.Car;

public class CarDaoImpl extends BaseDaoImpl<Car> implements CarDao {
	public CarDaoImpl() {
		super(Car.class);
	}
}
