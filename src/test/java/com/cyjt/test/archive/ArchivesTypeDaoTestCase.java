package com.cyjt.test.archive;

import com.cyjt.oa.dao.archive.ArchivesTypeDao;
import com.cyjt.oa.model.archive.ArchivesType;
import com.cyjt.test.BaseTestCase;
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
