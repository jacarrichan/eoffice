package com.cyjt.test.archive;

import com.cyjt.oa.dao.archive.ArchTemplateDao;
import com.cyjt.oa.model.archive.ArchTemplate;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ArchTemplateDaoTestCase extends BaseTestCase {

	@Resource
	private ArchTemplateDao archTemplateDao;

	@Test
	@Rollback(false)
	public void add() {
		ArchTemplate archTemplate = new ArchTemplate();

		this.archTemplateDao.save(archTemplate);
	}
}
