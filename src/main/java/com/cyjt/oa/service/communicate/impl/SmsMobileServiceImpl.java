package com.cyjt.oa.service.communicate.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.smslib.Message.MessageEncodings;
import org.smslib.OutboundMessage;
import org.smslib.Service;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.core.util.BeanUtil;
import com.cyjt.core.util.GmsUtil;
import com.cyjt.oa.dao.communicate.SmsMobileDao;
import com.cyjt.oa.model.communicate.SmsHistory;
import com.cyjt.oa.model.communicate.SmsMobile;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.service.communicate.SmsHistoryService;
import com.cyjt.oa.service.communicate.SmsMobileService;
import com.cyjt.oa.service.system.AppUserService;

public class SmsMobileServiceImpl extends BaseServiceImpl<SmsMobile> implements SmsMobileService {

	@Resource
	private AppUserService appUserService;

	@Resource
	private SmsMobileService smsMobileService;

	@Resource
	private SmsHistoryService smsHistoryService;

	private SmsMobileDao dao;

	public SmsMobileServiceImpl(SmsMobileDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public SmsMobile save(SmsMobile entity) {
		this.dao.save(entity);
		return entity;
	}

	public void saveSms(String userIds, String content) {
		if (StringUtils.isNotEmpty(userIds)) {
			String[] ids = userIds.split(",");
			for (String id : ids) {
				AppUser recipients = this.appUserService.get(new Long(id));

				SmsMobile smsMobile = new SmsMobile();
				smsMobile.setPhoneNumber(recipients.getMobile());
				smsMobile.setSendTime(new Date());
				smsMobile.setRecipients(recipients.getFullname());
				smsMobile.setSmsContent(content);
				smsMobile.setUserId(AppUser.SYSTEM_USER);
				smsMobile.setUserName("系统");
				smsMobile.setStatus(SmsMobile.STATUS_NOT_SENDED);

				this.smsMobileService.save(smsMobile);
			}
		}
	}

	public void sendSms() {
		try {
			GmsUtil.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<SmsMobile> list = this.smsMobileService.getNeedToSend();
		this.send(list);
	}

	public void send(List<SmsMobile> list) {

		if (list.size() > 0) {
			try {
				Service.getInstance().startService();

				for (SmsMobile smsMobile : list) {
					OutboundMessage msg = new OutboundMessage(smsMobile.getPhoneNumber(), smsMobile.getSmsContent());
					msg.setEncoding(MessageEncodings.ENCUCS2);
					Service.getInstance().sendMessage(msg);
					smsMobile.setStatus(SmsMobile.STATUS_SENDED);

					SmsHistory smsHistory = new SmsHistory();
					BeanUtil.copyNotNullProperties(smsHistory, smsMobile);
					smsHistory.setSmsId(null);
					this.smsHistoryService.merge(smsHistory);

					this.smsMobileService.remove(smsMobile.getSmsId());
				}

				Service.getInstance().stopService();
			} catch (Exception e) {
				this.logger.error(e);
			}
		}
	}

	public List<SmsMobile> getNeedToSend() {
		return this.dao.getNeedToSend();
	}

	public void sendOneSms(SmsMobile smsMobile) {
		try {
			Service.getInstance().startService();

			OutboundMessage msg = new OutboundMessage(smsMobile.getPhoneNumber(), smsMobile.getSmsContent());
			msg.setEncoding(MessageEncodings.ENCUCS2);
			Service.getInstance().sendMessage(msg);
			smsMobile.setStatus(SmsMobile.STATUS_SENDED);

			SmsHistory smsHistory = new SmsHistory();
			BeanUtil.copyNotNullProperties(smsHistory, smsMobile);
			smsHistory.setSmsId(null);
			this.smsHistoryService.merge(smsHistory);

			this.smsMobileService.remove(smsMobile.getSmsId());

			Service.getInstance().stopService();
		} catch (Exception e) {
			this.logger.error(e);
		}
	}
}