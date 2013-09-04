package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.FunUrlDao;
import com.palmelf.eoffice.model.system.FunUrl;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class FunUrlDaoTestCase extends BaseTestCase {

	@Resource
	private FunUrlDao funUrlDao;

	@Test
	@Rollback(false)
	public void add() {
		FunUrl funUrl = new FunUrl();

		this.funUrlDao.save(funUrl);
	}
}
