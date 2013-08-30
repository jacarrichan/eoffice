package com.cyjt.test.archive;

import com.cyjt.oa.dao.archive.ArchivesDao;
import com.cyjt.oa.model.archive.Archives;
import com.cyjt.test.BaseTestCase;
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
