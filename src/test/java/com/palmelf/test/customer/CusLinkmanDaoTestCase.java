package com.palmelf.test.customer;

import com.palmelf.eoffice.dao.customer.CusLinkmanDao;
import com.palmelf.eoffice.model.customer.CusLinkman;
import com.palmelf.test.BaseTestCase;

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
