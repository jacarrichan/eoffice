package com.palmelf.test.hrm;

import com.palmelf.eoffice.dao.hrm.EmpProfileDao;
import com.palmelf.eoffice.model.hrm.EmpProfile;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class EmpProfileDaoTestCase extends BaseTestCase {

	@Resource
	private EmpProfileDao empProfileDao;

	@Test
	@Rollback(false)
	public void add() {
		EmpProfile empProfile = new EmpProfile();

		this.empProfileDao.save(empProfile);
	}
}
