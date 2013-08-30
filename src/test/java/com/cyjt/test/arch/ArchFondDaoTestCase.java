package com.cyjt.test.arch;

import com.cyjt.oa.dao.arch.ArchFondDao;
import com.cyjt.oa.model.arch.ArchFond;
import com.cyjt.test.BaseTestCase;
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
