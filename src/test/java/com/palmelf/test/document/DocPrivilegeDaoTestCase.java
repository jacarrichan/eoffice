package com.palmelf.test.document;

import com.palmelf.eoffice.dao.document.DocFolderDao;
import com.palmelf.eoffice.dao.document.DocPrivilegeDao;
import com.palmelf.eoffice.dao.document.DocumentDao;
import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.test.BaseTestCase;

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
