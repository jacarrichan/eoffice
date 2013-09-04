package com.palmelf.test.arch;

import com.palmelf.eoffice.dao.arch.ArchFondDao;
import com.palmelf.eoffice.model.arch.ArchFond;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchFondDaoTestCase extends BaseTestCase {

	@Resource
	private ArchFondDao archFondDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchFond archFond = new ArchFond();

		this.archFondDao.save(archFond);
	}
}
