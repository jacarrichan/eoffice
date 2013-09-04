package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.OfficeGoodsTypeDao;
import com.palmelf.eoffice.model.admin.OfficeGoodsType;
import com.palmelf.test.BaseTestCase;

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
