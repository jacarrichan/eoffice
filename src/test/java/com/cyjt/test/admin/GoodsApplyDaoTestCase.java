package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.GoodsApplyDao;
import com.cyjt.oa.model.admin.GoodsApply;
import com.cyjt.test.BaseTestCase;
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
