package com.cyjt.test.archive;

import com.cyjt.oa.dao.archive.DocHistoryDao;
import com.cyjt.oa.model.archive.DocHistory;
import com.cyjt.test.BaseTestCase;
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
