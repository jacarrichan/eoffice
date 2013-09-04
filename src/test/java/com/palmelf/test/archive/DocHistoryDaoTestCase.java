package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.DocHistoryDao;
import com.palmelf.eoffice.model.archive.DocHistory;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DocHistoryDaoTestCase extends BaseTestCase {

	@Resource
	private DocHistoryDao docHistoryDao;

	@Test
	@Rollback(false)
	public void add() {
		DocHistory docHistory = new DocHistory();

		this.docHistoryDao.save(docHistory);
	}
}
