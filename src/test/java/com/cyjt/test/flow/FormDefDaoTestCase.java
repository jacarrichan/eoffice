package com.cyjt.test.flow;

import com.cyjt.oa.dao.flow.FormDefDao;
import com.cyjt.oa.model.flow.FormDef;
import com.cyjt.test.BaseTestCase;
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
