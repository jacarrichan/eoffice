package com.cyjt.test.archive;

import com.cyjt.oa.dao.archive.ArchRecTypeDao;
import com.cyjt.oa.model.archive.ArchRecType;
import com.cyjt.test.BaseTestCase;
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
