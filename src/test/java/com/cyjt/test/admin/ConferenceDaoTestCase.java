package com.cyjt.test.admin;

import com.cyjt.oa.dao.admin.ConferenceDao;
import com.cyjt.oa.model.admin.Conference;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class ConferenceDaoTestCase extends BaseTestCase {

	@Resource
	private ConferenceDao conferenceDao;

	@Test
	@Rollback(false)
	public void add() {
		Conference conference = new Conference();

		this.conferenceDao.save(conference);
	}
}
