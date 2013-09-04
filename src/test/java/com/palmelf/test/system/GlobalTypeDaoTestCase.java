package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.GlobalTypeDao;
import com.palmelf.eoffice.model.system.GlobalType;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;

public class GlobalTypeDaoTestCase extends BaseTestCase {

	@Resource
	private GlobalTypeDao globalTypeDao;

	@Override
	@Test
	public void test() {
		GlobalType g = new GlobalType();
		g.setCatKey("3");
		g.setDepth(Integer.valueOf(1));
		g.setNodeKey("3");
		g.setParentId(new Long(0L));
		g.setPath("0.");
		g.setSn(Integer.valueOf(1));
		g.setTypeName("xxx");
		g.setVersion(Integer.valueOf(1));
		this.globalTypeDao.save(g);
		Long id = g.getProTypeId();

		g.setCatKey("3");
		g.setDepth(Integer.valueOf(1));
		g.setNodeKey("3");
		g.setParentId(new Long(0L));
		g.setPath("0." + id + ".");
		g.setSn(Integer.valueOf(1));
		g.setTypeName("xxx");
		g.setVersion(Integer.valueOf(1));
		this.globalTypeDao.save(g);
	}
}
