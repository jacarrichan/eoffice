package com.cyjt.oa.service.info.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.InMessageDao;
import com.cyjt.oa.model.info.InMessage;
import com.cyjt.oa.model.info.ShortMessage;
import com.cyjt.oa.service.info.InMessageService;
import java.util.Date;
import java.util.List;

public class InMessageServiceImpl extends BaseServiceImpl<InMessage> implements
		InMessageService {
	private InMessageDao dao;

	public InMessageServiceImpl(InMessageDao dao) {
		super(dao);
		this.dao = dao;
	}

	public InMessage findByRead(Long userId) {
		return this.dao.findByRead(userId);
	}

	public Integer findByReadFlag(Long userId) {
		return this.dao.findByReadFlag(userId);
	}

	public List<InMessage> findAll(Long userId, PagingBean pb) {
		return this.dao.findAll(userId, pb);
	}

	public List findByUser(Long userId, PagingBean pb) {
		return this.dao.findByUser(userId, pb);
	}

	public List<InMessage> searchInMessage(Long userId, InMessage inMessage,
			ShortMessage shortMessage, Date from, Date to, PagingBean pb) {
		return this.dao.searchInMessage(userId, inMessage, shortMessage, from,
				to, pb);
	}

	public InMessage findLatest(Long userId) {
		return this.dao.findLatest(userId);
	}
}
