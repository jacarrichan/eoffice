package com.cyjt.test.arch;

import com.cyjt.oa.dao.arch.ArchRollDao;
import com.cyjt.oa.model.arch.ArchRoll;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchRollDaoTestCase extends BaseTestCase {

	@Resource
	private ArchRollDao archRollDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchRoll archRoll = new ArchRoll();

		this.archRollDao.save(archRoll);
	}
}
