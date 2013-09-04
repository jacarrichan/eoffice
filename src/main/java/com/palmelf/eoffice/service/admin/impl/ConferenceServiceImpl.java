package com.palmelf.eoffice.service.admin.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.admin.ConferenceDao;
import com.palmelf.eoffice.model.admin.Conference;
import com.palmelf.eoffice.service.admin.ConferenceService;

import java.util.Date;
import java.util.List;

public class ConferenceServiceImpl extends BaseServiceImpl<Conference>
		implements ConferenceService {
	private ConferenceDao dao;

	public ConferenceServiceImpl(ConferenceDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<Conference> getConfTopic(String confTopic, PagingBean pb) {
		return this.dao.getConfTopic(confTopic, pb);
	}

	public String baseUserIdSearchFullName(String userIds) {
		return this.dao.baseUserIdSearchFullName(userIds);
	}

	public Conference send(Conference conference, String view, String updater,
			String summary, String fileIds) {
		conference.setCreatetime(new Date());
		conference.setSendtime(new Date());
		return this.dao.send(conference, view, updater, summary, fileIds);
	}

	public Conference temp(Conference conference, String view, String updater,
			String summary, String fileIds) {
		conference.setStatus((short) 0);
		conference.setCreatetime(new Date());
		return this.dao.temp(conference, view, updater, summary, fileIds);
	}

	public String judgeBoardRoomNotUse(Date startTime, Date endTime, Long roomId) {
		return this.dao.judgeBoardRoomNotUse(startTime, endTime, roomId);
	}

	public String apply(Long confId, String checkReason, boolean bo) {
		return this.dao.apply(confId, checkReason, bo);
	}
}
