package com.palmelf.eoffice.service.info.impl;

import com.palmelf.core.Constants;
import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.info.InMessageDao;
import com.palmelf.eoffice.dao.info.ShortMessageDao;
import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.model.info.InMessage;
import com.palmelf.eoffice.model.info.ShortMessage;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.info.ShortMessageService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class ShortMessageServiceImpl extends BaseServiceImpl<ShortMessage>
		implements ShortMessageService {
	private ShortMessageDao messageDao;

	@Resource
	private InMessageDao inMessageDao;

	@Resource
	private AppUserDao appUserDao;

	public ShortMessageServiceImpl(ShortMessageDao dao) {
		super(dao);
		this.messageDao = dao;
	}

	public List<ShortMessage> findAll(Long userId, PagingBean pb) {
		return this.messageDao.findAll(userId, pb);
	}

	public List<ShortMessage> findByUser(Long userId) {
		return this.messageDao.findByUser(userId);
	}

	public List searchShortMessage(Long userId, ShortMessage shortMessage,
			Date from, Date to, PagingBean pb) {
		return this.messageDao.searchShortMessage(userId, shortMessage, from,
				to, pb);
	}

	public ShortMessage save(Long senderId, String receiveIds, String content,
			Short msgType) {
		ShortMessage shortMessage = new ShortMessage();
		shortMessage.setContent(content);
		shortMessage.setMsgType(msgType);
		AppUser curUser = this.appUserDao.get(senderId);
		shortMessage.setSender(curUser.getFullname());
		shortMessage.setSenderId(curUser.getUserId());
		shortMessage.setSendTime(new Date());

		this.messageDao.save(shortMessage);
		String[] reIds = null;
		if (StringUtils.isNotEmpty(receiveIds)) {
			reIds = receiveIds.split("[,]");
		}
		if (reIds != null) {
			for (String userId : reIds) {
				InMessage inMsg = new InMessage();
				inMsg.setDelFlag(Constants.FLAG_UNDELETED);
				inMsg.setReadFlag(InMessage.FLAG_UNREAD);
				inMsg.setShortMessage(shortMessage);
				AppUser receiveUser = this.appUserDao.get(new Long(userId));

				inMsg.setUserId(receiveUser.getUserId());
				inMsg.setUserFullname(receiveUser.getFullname());
				this.inMessageDao.save(inMsg);
			}
		}

		return shortMessage;
	}
}
