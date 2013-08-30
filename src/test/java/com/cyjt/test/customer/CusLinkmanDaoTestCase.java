package com.cyjt.test.customer;

import com.cyjt.oa.dao.customer.CusLinkmanDao;
import com.cyjt.oa.model.customer.CusLinkman;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class CusLinkmanDaoTestCase extends BaseTestCase {

	@Resource
	private CusLinkmanDao cusLinkmanDao;

	@Test
	@Rollback(false)
	public void add() {
		CusLinkman cusLinkman = new CusLinkman();

		this.cusLinkmanDao.save(cusLinkman);
	}
}
