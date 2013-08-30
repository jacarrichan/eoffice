package com.cyjt.test.arch;

import com.cyjt.oa.dao.arch.RollFileListDao;
import com.cyjt.oa.model.arch.RollFileList;
import com.cyjt.test.BaseTestCase;
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
