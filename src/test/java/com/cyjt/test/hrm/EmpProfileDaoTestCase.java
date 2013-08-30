package com.cyjt.test.hrm;

import com.cyjt.oa.dao.hrm.EmpProfileDao;
import com.cyjt.oa.model.hrm.EmpProfile;
import com.cyjt.test.BaseTestCase;
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
