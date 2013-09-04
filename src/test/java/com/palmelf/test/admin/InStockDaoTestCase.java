package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.InStockDao;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;

public class InStockDaoTestCase extends BaseTestCase {

	@Resource
	private InStockDao inStockDao;

	@Test
	public void test1() {
		Integer inCount = this.inStockDao.findInCountByBuyId(Long.valueOf(1L));
		System.out.println(inCount);
	}
}
