package com.palmelf.eoffice.action.info;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.info.InMessage;
import com.palmelf.eoffice.model.info.ShortMessage;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.info.InMessageService;
import com.palmelf.eoffice.service.info.ShortMessageService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class InMessageAction extends BaseAction {
	static short HAVE_DELETE = 1;
	private InMessage inMessage;
	private ShortMessage shortMessage;
	private Date from;
	private Date to;

	@Resource
	private InMessageService inMessageService;

	@Resource
	private ShortMessageService shortMessageService;

	public InMessage getInMessage() {
		return this.inMessage;
	}

	public void setInMessage(InMessage inMessage) {
		this.inMessage = inMessage;
	}

	public ShortMessage getShortMessage() {
		return this.shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
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

	@Override
	public String list() {
		PagingBean pb = this.getInitPagingBean();
		AppUser appUser = ContextUtil.getCurrentUser();

		List list = this.inMessageService.searchInMessage(appUser.getUserId(),
				this.inMessage, this.shortMessage, this.from, this.to, pb);

		List<InMessage> inList = new ArrayList<InMessage>();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':"
				+ pb.getTotalItems() + ",result:");
		for (int i = 0; i < list.size(); i++) {
			InMessage inMessage = (InMessage) ((Object[]) list.get(i))[0];
			inList.add(inMessage);
		}

		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				new String[] { "shortMessage.sendTime" });
		buff.append(serializer.serialize(inList));
		buff.append("}");
		this.setJsonString(buff.toString());
		return "success";
	}

	public String know() {
		String strReceiveId = this.getRequest().getParameter("receiveId");
		Long receiveId = null;
		if (StringUtils.isNotEmpty(strReceiveId)) {
			receiveId = Long.valueOf(Long.parseLong(strReceiveId));
		}
		InMessage in = this.inMessageService.get(receiveId);
		in.setReadFlag(Short.valueOf((short) 1));
		this.inMessageService.save(in);
		this.setJsonString("{success:true}");
		return "success";
	}

	public String multiRemove() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.inMessage = (this.inMessageService.get(Long.valueOf(Long
						.parseLong(id))));
				this.inMessage.setDelFlag(Short
						.valueOf(InMessageAction.HAVE_DELETE));
				this.inMessageService.save(this.inMessage);
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String reply() {
		String strReplyId = this.getRequest().getParameter("receiveId");
		if (StringUtils.isNotEmpty(strReplyId)) {
			Long replyId = Long.valueOf(Long.parseLong(strReplyId));
			this.inMessage = (this.inMessageService.get(replyId));
			StringBuffer buff = new StringBuffer("{success:true,data:[");
			buff.append(
					"{'messageId':"
							+ this.inMessage.getShortMessage().getMessageId()
							+ ",'senderId':'"
							+ this.inMessage.getShortMessage().getSenderId()
							+ "','sender':'"
							+ this.inMessage.getShortMessage().getSender()
							+ "'}").append("]}");
			this.setJsonString(buff.toString());
		} else {
			this.setJsonString("{success:false}");
		}
		return "success";
	}

	public String read() {
		Long userId = ContextUtil.getCurrentUser().getUserId();
		boolean flag = false;
		if (userId != null) {
			this.inMessage = this.inMessageService.findByRead(userId);
			if (this.inMessage == null) {
				flag = true;
				this.inMessage = this.inMessageService.findLatest(userId);
			}
			if (this.inMessage != null) {
				this.inMessage.setReadFlag(InMessage.FLAG_READ);
				this.inMessageService.save(this.inMessage);
				this.shortMessage = this.inMessage.getShortMessage();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(this.shortMessage.getSendTime());
				StringBuffer buff = new StringBuffer("{success:true,message:");
				buff.append("{'receiveId':" + this.inMessage.getReceiveId()
						+ ",'messageId':" + this.shortMessage.getMessageId()
						+ ",'senderId':" + this.shortMessage.getSenderId()
						+ ",'sender':'" + this.shortMessage.getSender()
						+ "','content':'"
						+ this.shortMessage.getContent().replace("\n", " ")
						+ "','sendTime':'" + date + "','msgType':"
						+ this.shortMessage.getMsgType());
				if (!flag) {
					InMessage in = this.inMessageService.findByRead(userId);
					if (in != null) {
						buff.append(",haveNext:true");
					} else {
						buff.append(",haveNext:false");
					}
				} else {
					buff.append(",haveNext:false");
				}
				buff.append("}}");
				this.setJsonString(buff.toString());
			} else {
				this.setJsonString("{success:false}");
			}
		} else {
			this.setJsonString("{success:true}");
		}
		return "success";
	}

	public String count() {

		// Integer in = this.inMessageService.findByReadFlag(ContextUtil
		// .getCurrentUser().getUserId());
		Integer in = 0;
		this.setJsonString("{success:true,count:'" + in + "'}");
		return "success";
	}

	public String detail() {
		String strReceiveId = this.getRequest().getParameter("receiveId");
		if (StringUtils.isNotEmpty(strReceiveId)) {
			Long receiveId = new Long(strReceiveId);
			this.inMessage = (this.inMessageService.get(receiveId));
			this.inMessage.setReadFlag(Short.valueOf((short) 1));
			this.inMessageService.save(this.inMessage);
		}
		return "detail";
	}

	public String display() {
		PagingBean pb = new PagingBean(0, 8);
		AppUser appUser = ContextUtil.getCurrentUser();
		List list = this.shortMessageService.searchShortMessage(
				appUser.getUserId(), null, null, null, pb);
		List<InMessage> inList = new ArrayList<InMessage>();
		new StringBuffer("{success:true,'totalCounts':" + pb.getTotalItems()
				+ ",result:");
		for (int i = 0; i < list.size(); i++) {
			InMessage inMessage = (InMessage) ((Object[]) list.get(i))[0];
			inList.add(inMessage);
		}

		this.getRequest().setAttribute("messageList", inList);
		return "display";
	}

	public String multiRead() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.inMessage = (this.inMessageService.get(Long.valueOf(Long
						.parseLong(id))));
				this.inMessage.setReadFlag(InMessage.FLAG_READ);
				this.inMessageService.save(this.inMessage);
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}
}
