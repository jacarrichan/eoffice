package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.ArchivesDao;
import com.palmelf.eoffice.model.archive.Archives;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchivesDaoTestCase extends BaseTestCase {

	@Resource
	private ArchivesDao archivesDao;

	@Test
	@Rollback(false)
	public void add() {
		Archives archives = new Archives();

		this.archivesDao.save(archives);
	}
}
