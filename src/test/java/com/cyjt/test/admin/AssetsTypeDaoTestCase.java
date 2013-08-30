package com.cyjt.test.admin;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.cyjt.oa.dao.admin.AssetsTypeDao;
import com.cyjt.oa.model.admin.AssetsType;
import com.cyjt.test.BaseTestCase;

public class AssetsTypeDaoTestCase extends BaseTestCase {

	@Resource
	private AssetsTypeDao assetsTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		AssetsType assetsType = new AssetsType();
		assetsType.setTypeName("哈哈");
		this.assetsTypeDao.save(assetsType);
	}

	@Test
	public void find() {
		List<AssetsType> list = this.assetsTypeDao.getAll();
		for (AssetsType assetsType : list) {
			System.out.println(assetsType.getTypeName());
		}
	}
}
