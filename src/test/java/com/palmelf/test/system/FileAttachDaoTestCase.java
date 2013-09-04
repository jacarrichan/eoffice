package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.FileAttachDao;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class FileAttachDaoTestCase extends BaseTestCase {

	@Resource
	private FileAttachDao fileAttachDao;

	@Test
	@Rollback(false)
	public void add() {
		FileAttach fileAttach = new FileAttach();

		this.fileAttachDao
				.removeByPath("communicate/mail/200910/2a4367669a1a4ea2b811013ceed199ce.png");
	}
}
