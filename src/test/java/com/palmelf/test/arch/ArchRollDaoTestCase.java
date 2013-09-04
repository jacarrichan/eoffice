package com.palmelf.test.arch;

import com.palmelf.eoffice.dao.arch.ArchRollDao;
import com.palmelf.eoffice.model.arch.ArchRoll;
import com.palmelf.test.BaseTestCase;

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
