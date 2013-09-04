package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.AppFunctionDao;
import com.palmelf.eoffice.model.system.AppFunction;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class AppFunctionDaoTestCase extends BaseTestCase {

	@Resource
	private AppFunctionDao appFunctionDao;

	@Test
	@Rollback(false)
	public void add() {
		AppFunction appFunction = new AppFunction();

		this.appFunctionDao.save(appFunction);
	}
}
