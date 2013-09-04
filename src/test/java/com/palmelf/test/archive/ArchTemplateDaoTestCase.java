package com.palmelf.test.archive;

import com.palmelf.eoffice.dao.archive.ArchTemplateDao;
import com.palmelf.eoffice.model.archive.ArchTemplate;
import com.palmelf.test.BaseTestCase;

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
