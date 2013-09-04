package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.CartRepairDao;
import com.palmelf.eoffice.model.admin.CartRepair;
import com.palmelf.test.BaseTestCase;

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
