package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.OfficeGoodsTypeDao;
import com.cyjt.oa.model.admin.OfficeGoodsType;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class OfficeGoodsTypeDaoTestCase extends BaseTestCase {

	@Resource
	private OfficeGoodsTypeDao officeGoodsTypeDao;

	@Test
	@Rollback(false)
	public void add() {
		OfficeGoodsType officeGoodsType = new OfficeGoodsType();

		this.officeGoodsTypeDao.save(officeGoodsType);
	}
}
