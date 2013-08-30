package com.cyjt.test.document;

import com.cyjt.oa.dao.document.DocFolderDao;
import com.cyjt.oa.dao.document.DocPrivilegeDao;
import com.cyjt.oa.dao.document.DocumentDao;
import com.cyjt.oa.dao.system.AppUserDao;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;

public class DocPrivilegeDaoTestCase extends BaseTestCase {

	@Resource
	private DocPrivilegeDao docPrivilegeDao;

	@Resource
	private DocumentDao documentDao;

	@Resource
	private DocFolderDao docFolderDao;

	@Resource
	private AppUserDao dao;

	@Test
	public void str() {
		AppUser user = this.dao.get(Long.valueOf(2L));
		Integer right = this.docPrivilegeDao.getRightsByDocument(user,
				Long.valueOf(1L));
		System.out.println(right);
	}
}
