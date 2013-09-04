package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.DepreTypeDao;
import com.palmelf.eoffice.model.admin.DepreType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DepreTypeDaoTestCase extends BaseTestCase {

	@Resource
	private DepreTypeDao depreTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		DepreType depreType = new DepreType();

		this.depreTypeDao.save(depreType);
	}
}
