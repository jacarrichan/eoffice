package com.palmelf.eoffice.action.info;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.util.StringUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.InMessage;
import com.palmelf.eoffice.model.info.ShortMessage;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.info.InMessageService;
import com.palmelf.eoffice.service.info.ShortMessageService;
import com.palmelf.eoffice.service.system.AppUserService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class ShortMessageAction extends BaseAction {
	static short NOT_DELETE = 0;
	private ShortMessage shortMessage;
	private Date from;
	private Date to;
	private List<InMessage> inList = new ArrayList<InMessage>();

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	private InMessageService inMessageService;

	@Resource
	private AppUserService appUserService;

	public List<InMessage> getInList() {
		return this.inList;
	}

	public void setInList(List<InMessage> inList) {
		this.inList = inList;
	}

	public Date getFrom() {
		return this.from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return this.to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public ShortMessage getShortMessage() {
		return this.shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
	}

	@Override
	public String list() {
		PagingBean pb = this.getInitPagingBean();
		AppUser appUser = ContextUtil.getCurrentUser();
		List<?> list = this.shortMessageService.searchShortMessage(
				appUser.getUserId(), this.shortMessage, this.from, this.to, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':"
				+ pb.getTotalItems() + ",result:");
		List<InMessage> inList = new ArrayList<InMessage>();
		for (int i = 0; i < list.size(); i++) {
			InMessage inMessage = (InMessage) ((Object[]) list.get(i))[0];
			inList.add(inMessage);
		}
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				new String[] {"shortMessage.sendTime"});
		buff.append(serializer.serialize(inList));
		
		buff.append("}");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String send() {
		String reId = this.getRequest().getParameter("userId");
		String content = this.getRequest().getParameter("content");
		String senderId = this.getRequest().getParameter("senderId");

		AppUser appUser = ContextUtil.getCurrentUser();
		if ((StringUtils.isNotEmpty(reId)) && (StringUtils.isNotEmpty(content))) {
			String[] st = reId.split(",");
			ShortMessage message = new ShortMessage();
			message.setContent(content);
			message.setMsgType(Short.valueOf((short) 1));
			if ((StringUtils.isNotEmpty(senderId))
					&& (StringUtil.isNum(senderId))) {
				AppUser sender = this.appUserService.get(new Long(senderId));
				message.setSender(sender.getFullname());
				message.setSenderId(sender.getUserId());
			} else {
				message.setSenderId(appUser.getUserId());
				message.setSender(appUser.getFullname());
			}
			message.setSendTime(new Date());
			this.shortMessageService.save(message);
			for (String element : st) {
				InMessage in = new InMessage();
				in.setUserId(Long.valueOf(Long.parseLong(element)));
				AppUser user = this.appUserService.get(Long.valueOf(Long
						.parseLong(element)));
				in.setUserFullname(user.getFullname());
				in.setDelFlag(Short.valueOf(ShortMessageAction.NOT_DELETE));
				in.setReadFlag(Short.valueOf((short) 0));
				in.setShortMessage(message);
				this.inMessageService.save(in);
			}
			this.setJsonString("{success:true}");
		} else {
			this.setJsonString("{success:false}");
		}
		return "success";
	}
}
