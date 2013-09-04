package com.palmelf.test.flow;

import com.palmelf.eoffice.dao.flow.ProcessRunDao;
import com.palmelf.eoffice.model.flow.ProcessRun;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ProcessRunDaoTestCase extends BaseTestCase {

	@Resource
	private ProcessRunDao processRunDao;

	@Test
	@Rollback(false)
	public void add() {
		ProcessRun processRun = new ProcessRun();

		this.processRunDao.save(processRun);
	}
}
