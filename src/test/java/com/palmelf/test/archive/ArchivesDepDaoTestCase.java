package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.ArchivesDepDao;
import com.palmelf.eoffice.model.archive.ArchivesDep;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchivesDepDaoTestCase extends BaseTestCase {

	@Resource
	private ArchivesDepDao archivesDepDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchivesDep archivesDep = new ArchivesDep();

		this.archivesDepDao.save(archivesDep);
	}
}
