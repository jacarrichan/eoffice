package com.cyjt.test.archive;

import com.cyjt.oa.dao.archive.ArchivesDepDao;
import com.cyjt.oa.model.archive.ArchivesDep;
import com.cyjt.test.BaseTestCase;
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
