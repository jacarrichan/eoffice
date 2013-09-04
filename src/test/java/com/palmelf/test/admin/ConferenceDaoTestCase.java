package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.ConferenceDao;
import com.palmelf.eoffice.model.admin.Conference;
import com.palmelf.test.BaseTestCase;

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
