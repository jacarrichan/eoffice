package com.palmelf.test.flow;

import com.palmelf.eoffice.dao.flow.FormDefDao;
import com.palmelf.eoffice.model.flow.FormDef;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class FormDefDaoTestCase extends BaseTestCase {

	@Resource
	private FormDefDao formDefDao;

	@Test
	@Rollback(false)
	public void add() {
		FormDef formDef = new FormDef();

		this.formDefDao.save(formDef);
	}
}
