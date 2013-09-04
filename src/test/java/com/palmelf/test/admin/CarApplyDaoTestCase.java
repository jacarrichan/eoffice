package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.CarApplyDao;
import com.palmelf.eoffice.model.admin.CarApply;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class CarApplyDaoTestCase extends BaseTestCase {

	@Resource
	private CarApplyDao carApplyDao;

	@Test
	@Rollback(false)
	public void add() {
		CarApply carApply = new CarApply();

		this.carApplyDao.save(carApply);
	}
}
