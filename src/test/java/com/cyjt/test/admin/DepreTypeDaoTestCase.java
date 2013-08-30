package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.DepreTypeDao;
import com.cyjt.oa.model.admin.DepreType;
import com.cyjt.test.BaseTestCase;
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
