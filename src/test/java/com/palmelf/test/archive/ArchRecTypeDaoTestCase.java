package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.ArchRecTypeDao;
import com.palmelf.eoffice.model.archive.ArchRecType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchRecTypeDaoTestCase extends BaseTestCase {

	@Resource
	private ArchRecTypeDao archRecTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchRecType archRecType = new ArchRecType();

		this.archRecTypeDao.save(archRecType);
	}
}
