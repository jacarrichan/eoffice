package com.palmelf.test.flow;

import com.palmelf.eoffice.dao.flow.ProcessFormDao;
import com.palmelf.eoffice.model.flow.ProcessForm;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ProcessFormDaoTestCase extends BaseTestCase {

	@Resource
	private ProcessFormDao processFormDao;

	@Test
	@Rollback(false)
	public void add() {
		ProcessForm processForm = new ProcessForm();

		this.processFormDao.save(processForm);
	}
}
