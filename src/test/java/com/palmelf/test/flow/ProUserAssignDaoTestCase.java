package com.palmelf.test.flow;

import com.palmelf.eoffice.dao.flow.ProUserAssignDao;
import com.palmelf.eoffice.model.flow.ProUserAssign;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ProUserAssignDaoTestCase extends BaseTestCase {

	@Resource
	private ProUserAssignDao proUserAssignDao;

	@Test
	@Rollback(false)
	public void add() {
		ProUserAssign proUserAssign = new ProUserAssign();

		this.proUserAssignDao.save(proUserAssign);
	}
}
