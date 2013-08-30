package com.cyjt.oa.dao.admin.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.CarDao;
import com.cyjt.oa.model.admin.Car;

public class CarDaoImpl extends BaseDaoImpl<Car> implements CarDao {
	public CarDaoImpl() {
		super(Car.class);
	}
}
