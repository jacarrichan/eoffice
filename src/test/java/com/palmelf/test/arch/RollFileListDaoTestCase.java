package com.palmelf.test.arch;

import com.palmelf.eoffice.dao.arch.RollFileListDao;
import com.palmelf.eoffice.model.arch.RollFileList;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class RollFileListDaoTestCase extends BaseTestCase {

	@Resource
	private RollFileListDao rollFileListDao;

	@Test
	@Rollback(false)
	public void add() {
		RollFileList rollFileList = new RollFileList();

		this.rollFileListDao.save(rollFileList);
	}
}
