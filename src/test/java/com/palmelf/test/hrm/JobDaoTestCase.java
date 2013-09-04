package com.palmelf.test.hrm;

import com.palmelf.eoffice.dao.hrm.JobDao;
import com.palmelf.eoffice.model.hrm.Job;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class JobDaoTestCase extends BaseTestCase {

	@Resource
	private JobDao jobDao;

	@Test
	@Rollback(false)
	public void add() {
		Job job = new Job();

		this.jobDao.save(job);
	}
}
