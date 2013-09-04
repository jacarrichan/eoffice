package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.GoodsApplyDao;
import com.palmelf.eoffice.model.admin.GoodsApply;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class GoodsApplyDaoTestCase extends BaseTestCase {

	@Resource
	private GoodsApplyDao goodsApplyDao;

	@Test
	@Rollback(false)
	public void add() {
		GoodsApply goodsApply = new GoodsApply();

		this.goodsApplyDao.save(goodsApply);
	}
}
