package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.ArchivesTypeDao;
import com.palmelf.eoffice.model.archive.ArchivesType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchivesTypeDaoTestCase extends BaseTestCase {

	@Resource
	private ArchivesTypeDao archivesTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchivesType archivesType = new ArchivesType();

		this.archivesTypeDao.save(archivesType);
	}
}
