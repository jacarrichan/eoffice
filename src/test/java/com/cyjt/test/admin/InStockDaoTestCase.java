package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.InStockDao;
import com.cyjt.test.BaseTestCase;
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
