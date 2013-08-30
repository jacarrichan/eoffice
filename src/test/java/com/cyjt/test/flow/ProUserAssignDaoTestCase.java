package com.cyjt.test.flow;

import com.cyjt.oa.dao.flow.ProUserAssignDao;
import com.cyjt.oa.model.flow.ProUserAssign;
import com.cyjt.test.BaseTestCase;
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
