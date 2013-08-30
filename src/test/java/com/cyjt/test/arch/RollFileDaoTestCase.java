package com.cyjt.test.arch;

import com.cyjt.oa.dao.arch.RollFileDao;
import com.cyjt.oa.model.arch.RollFile;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class RollFileDaoTestCase extends BaseTestCase {

	@Resource
	private RollFileDao rollFileDao;

	@Test
	@Rollback(false)
	public void add() {
		RollFile rollFile = new RollFile();

		this.rollFileDao.save(rollFile);
	}
}
