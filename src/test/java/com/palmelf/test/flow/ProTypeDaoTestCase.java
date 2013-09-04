package com.palmelf.test.flow;

import com.palmelf.eoffice.dao.flow.ProTypeDao;
import com.palmelf.eoffice.model.flow.ProType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ProTypeDaoTestCase extends BaseTestCase {

	@Resource
	private ProTypeDao proTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		ProType proType = new ProType();

		this.proTypeDao.save(proType);
	}
}
