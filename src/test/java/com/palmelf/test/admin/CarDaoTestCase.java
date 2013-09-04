package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.CarDao;
import com.palmelf.eoffice.model.admin.Car;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class CarDaoTestCase extends BaseTestCase {

	@Resource
	private CarDao carDao;

	@Test
	@Rollback(false)
	public void add() {
		Car car = new Car();

		this.carDao.save(car);
	}
}
