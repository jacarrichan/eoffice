package com.cyjt.test.communicate;

import com.cyjt.oa.dao.communicate.MobileMsgDao;
import com.cyjt.oa.model.communicate.MobileMsg;
import com.cyjt.test.BaseTestCase;
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
