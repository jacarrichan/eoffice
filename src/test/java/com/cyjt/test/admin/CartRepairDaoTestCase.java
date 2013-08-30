package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.CartRepairDao;
import com.cyjt.oa.model.admin.CartRepair;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class CartRepairDaoTestCase extends BaseTestCase {

	@Resource
	private CartRepairDao cartRepairDao;

	@Test
	@Rollback(false)
	public void add() {
		CartRepair cartRepair = new CartRepair();

		this.cartRepairDao.save(cartRepair);
	}
}
