package com.palmelf.test.communicate;

import com.palmelf.eoffice.dao.communicate.MobileMsgDao;
import com.palmelf.eoffice.model.communicate.MobileMsg;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class MobileMsgDaoTestCase extends BaseTestCase {

	@Resource
	private MobileMsgDao mobileMsgDao;

	@Test
	@Rollback(false)
	public void add() {
		MobileMsg mobileMsg = new MobileMsg();

		this.mobileMsgDao.save(mobileMsg);
	}
}
