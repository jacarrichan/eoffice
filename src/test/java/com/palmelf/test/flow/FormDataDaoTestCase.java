package com.palmelf.test.flow;

import com.palmelf.eoffice.dao.flow.FormDataDao;
import com.palmelf.eoffice.model.flow.FormData;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class FormDataDaoTestCase extends BaseTestCase {

	@Resource
	private FormDataDao formDataDao;

	@Test
	@Rollback(false)
	public void add() {
		FormData formData = new FormData();

		this.formDataDao.save(formData);
	}
}
