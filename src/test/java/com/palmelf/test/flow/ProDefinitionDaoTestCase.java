package com.palmelf.test.flow;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.palmelf.eoffice.dao.flow.ProDefinitionDao;
import com.palmelf.eoffice.dao.flow.ProTypeDao;
import com.palmelf.eoffice.model.flow.ProDefinition;
import com.palmelf.eoffice.model.flow.ProType;
import com.palmelf.test.BaseTestCase;

public class ProDefinitionDaoTestCase extends BaseTestCase {

	@Resource
	private ProDefinitionDao proDefinitionDao;

	@Resource
	private ProTypeDao proTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		ProDefinition pro = new ProDefinition();

		pro.setDefXml("xml");
		pro.setDescription("descriptin");
		pro.setName("vtest");
		pro.setDrawDefXml("drawXml");
		pro.setCreatetime(new Date());

		ProType proType = this.proTypeDao.get(new Long(1L));

		pro.setProType(proType);
		pro.setDeployId("1");
		this.proDefinitionDao.save(pro);
	}

	public void get() {
		this.proDefinitionDao.getAll();
	}
}
