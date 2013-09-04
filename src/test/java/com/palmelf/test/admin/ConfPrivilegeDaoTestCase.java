package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.ConfPrivilegeDao;
import com.palmelf.eoffice.model.admin.ConfPrivilege;
import com.palmelf.test.BaseTestCase;

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
