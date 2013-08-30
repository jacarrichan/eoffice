package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.ConfPrivilegeDao;
import com.cyjt.oa.model.admin.ConfPrivilege;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ConfPrivilegeDaoTestCase extends BaseTestCase {

	@Resource
	private ConfPrivilegeDao confPrivilegeDao;

	@Test
	@Rollback(false)
	public void add() {
		ConfPrivilege confPrivilege = new ConfPrivilege();

		this.confPrivilegeDao.save(confPrivilege);
	}
}
