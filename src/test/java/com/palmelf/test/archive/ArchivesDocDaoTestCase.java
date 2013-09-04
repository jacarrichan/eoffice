package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.ArchivesDocDao;
import com.palmelf.eoffice.model.archive.ArchivesDoc;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchivesDocDaoTestCase extends BaseTestCase {

	@Resource
	private ArchivesDocDao archivesDocDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchivesDoc archivesDoc = new ArchivesDoc();

		this.archivesDocDao.save(archivesDoc);
	}
}
