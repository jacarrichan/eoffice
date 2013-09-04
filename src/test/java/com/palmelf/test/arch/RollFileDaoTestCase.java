package com.palmelf.test.arch;

import com.palmelf.eoffice.dao.arch.RollFileDao;
import com.palmelf.eoffice.model.arch.RollFile;
import com.palmelf.test.BaseTestCase;

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
