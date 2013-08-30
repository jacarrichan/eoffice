package com.cyjt.test.flow;

import com.cyjt.oa.dao.flow.ProcessFormDao;
import com.cyjt.oa.model.flow.ProcessForm;
import com.cyjt.test.BaseTestCase;
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
