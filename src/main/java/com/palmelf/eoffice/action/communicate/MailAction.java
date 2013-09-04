package com.palmelf.eoffice.action.communicate;

import com.google.gson.Gson;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.*;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.communicate.*;
import com.palmelf.eoffice.model.info.ShortMessage;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.communicate.*;
import com.palmelf.eoffice.service.info.InMessageService;
import com.palmelf.eoffice.service.info.ShortMessageService;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.FileAttachService;

import flexjson.JSONSerializer;
import java.util.*;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class MailAction extends BaseAction {

	static long FOLDER_ID_RECEIVE = 1L;
	static long FOLDER_ID_SEND = 2L;
	static long FOLDER_ID_DRAFT = 3L;
	static long FOLDER_ID_DELETE = 4L;
	static short HAVE_DELETE = 1;
	static short NOT_DELETE = 0;
	static short HAVE_READ = 1;
	static short NOT_READ = 0;
	static Short HAVE_REPLY = Short.valueOf((short) 1);
	static short NOT_REPLY = 0;
	static short SYSTEM_MESSAGE = 4;
	static short COMMON = 1;
	static short IS_DRAFT = 0;
	static short IS_MAIL = 1;
	@Resource
	private MailService mailService;
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private MailFolderService mailFolderService;
	@Resource
	private MailBoxService mailBoxService;
	@Resource
	private ShortMessageService shortMessageService;
	@Resource
	private InMessageService inMessageService;
	private Mail mail;
	private Long mailId;
	private AppUser appUser;
	private Long folderId;
	private Long boxId;
	private String sendMessage;
	private Long replyBoxId;
	private String boxIds;
	private Long fileId;

	public MailAction() {
	}

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getFolderId() {
		if (folderId == null)
			return Long.valueOf(1L);
		else
			return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public String getBoxIds() {
		return boxIds;
	}

	public void setBoxIds(String boxIds) {
		this.boxIds = boxIds;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Long getReplyBoxId() {
		return replyBoxId;
	}

	public void setReplyBoxId(Long replyBoxId) {
		this.replyBoxId = replyBoxId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		if (folderId == null || folderId.longValue() == FOLDER_ID_RECEIVE)
			setFolderId(new Long(FOLDER_ID_RECEIVE));
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addFilter("Q_mailFolder.folderId_L_EQ", folderId.toString());
		if (folderId.longValue() != FOLDER_ID_DELETE)
			filter.addFilter("Q_delFlag_SN_EQ", "0");
		filter.addSorted("sendTime", "desc");
		List list = mailBoxService.getAll(filter);
		Gson gson = new Gson();
		StringBuffer buff = (new StringBuffer("{success:true,'totalCounts':"))
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		for (Iterator iterator = list.iterator(); iterator.hasNext(); buff
				.append("'},")) {
			MailBox mailBoxTemp = (MailBox) iterator.next();
			Mail mailTemp = mailBoxTemp.getMail();
			buff.append("{boxId:'")
					.append(mailBoxTemp.getBoxId())
					.append("',sendTime:'")
					.append(mailBoxTemp.getSendTime())
					.append("',delFlag:'")
					.append(mailBoxTemp.getDelFlag())
					.append("',readFlag:'")
					.append(mailBoxTemp.getReadFlag())
					.append("',replyFlag:'")
					.append(mailBoxTemp.getReplyFlag())
					.append("',mailId:'")
					.append(mailTemp.getMailId())
					.append("',importantFlag:'")
					.append(mailTemp.getImportantFlag())
					.append("',mailStatus:'")
					.append(mailTemp.getMailStatus())
					.append("',fileIds:'")
					.append(mailTemp.getFileIds())
					.append("',subject:'")
					.append(gson.toJson(mailTemp.getSubject())
							.replace("\"", "")).append("',recipientNames:'")
					.append(mailTemp.getRecipientNames()).append("',sender:'")
					.append(mailTemp.getSender()).append("',content:'");
			String content = StringUtil.html2Text(mailTemp.getContent());
			content = gson.toJson(content).replace("\"", "");
			if (content.length() > 100)
				content = (new StringBuilder(String.valueOf(content.substring(
						0, 100)))).append("...").toString();
			buff.append(content);
		}

		if (list.size() > 0)
			buff.deleteCharAt(buff.length() - 1);
		buff.append("]}");
		jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		MailFolder deleteFolder = (MailFolder) mailFolderService.get(Long
				.valueOf(FOLDER_ID_DELETE));
		String ids[] = getRequest().getParameterValues("ids");
		if (ids != null)
			if (getFolderId().longValue() == FOLDER_ID_DELETE) {
				String as[];
				int k = (as = ids).length;
				for (int i = 0; i < k; i++) {
					String id = as[i];
					mailBoxService.remove(new Long(id));
				}

			} else {
				String as1[];
				int l = (as1 = ids).length;
				for (int j = 0; j < l; j++) {
					String id = as1[j];
					MailBox mailBoxDelete = (MailBox) mailBoxService
							.get(new Long(id));
					mailBoxDelete.setDelFlag(Short.valueOf(HAVE_DELETE));
					mailBoxDelete.setMailFolder(deleteFolder);
					mailBoxService.save(mailBoxDelete);
				}

			}
		jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		String opt = getRequest().getParameter("opt");
		MailBox mailBox = null;
		if (opt == null || "".equals(opt)) {
			mailBox = (MailBox) mailBoxService.get(boxId);
			getRequest().setAttribute("__haveNextMailFlag", "");
		} else {
			String folderId = getRequest().getParameter("folderId");
			if (folderId == null || "".equals(folderId))
				folderId = "1";
			QueryFilter filter = new QueryFilter(getRequest());
			filter.getPagingBean().setPageSize(1);
			List list = null;
			filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
					.getCurrentUserId().toString());
			filter.addFilter("Q_delFlag_SN_EQ", "0");
			filter.addFilter("Q_mailFolder.folderId_L_EQ", folderId);
			if (opt.equals("_next")) {
				filter.addFilter("Q_boxId_L_GT", boxId.toString());
				list = mailBoxService.getAll(filter);
				if (filter.getPagingBean().getStart().intValue() + 1 == filter
						.getPagingBean().getTotalItems())
					getRequest().setAttribute("__haveNextMailFlag", "endNext");
			} else if (opt.equals("_pre")) {
				filter.addFilter("Q_boxId_L_LT", boxId.toString());
				filter.addSorted("boxId", "desc");
				list = mailBoxService.getAll(filter);
				if (filter.getPagingBean().getStart().intValue() + 1 == filter
						.getPagingBean().getTotalItems())
					getRequest().setAttribute("__haveNextMailFlag", "endPre");
			}
			if (list.size() > 0)
				mailBox = (MailBox) list.get(0);
			else
				mailBox = (MailBox) mailBoxService.get(boxId);
		}
		setMail(mailBox.getMail());
		mailBox.setReadFlag(Short.valueOf(HAVE_READ));
		mailBoxService.save(mailBox);
		if (mail.getMailStatus().shortValue() != 1) {
			JSONSerializer serializer = new JSONSerializer();
			StringBuffer sb = new StringBuffer(
					"{success:true,totalCounts:1,data:[");
			sb.append(serializer.exclude(
					new String[] { "class", "mail.appUser",
							"appUser.department", "mailFolder.appUser" })
					.serialize(mail));
			sb.append("]}");
			setJsonString(sb.toString());
			return "success";
		} else {
			getRequest().setAttribute("mail", mail);
			getRequest().setAttribute("boxId", mailBox.getBoxId());
			getRequest().setAttribute("mailAttachs", mail.getMailAttachs());
			return "detail";
		}
	}

	public String save() {
		if (mail.getMailStatus().shortValue() == IS_MAIL) {
			if (StringUtils.isEmpty(mail.getRecipientIDs())) {
				setJsonString("{failure:true,msg:'收件人不能为空!'}");
				return "success";
			}
			if (StringUtils.isEmpty(mail.getSubject())) {
				setJsonString("{failure:true,msg:'邮件主题不能为空!'}");
				return "success";
			}
			if (StringUtils.isEmpty(mail.getContent())) {
				setJsonString("{failure:true,msg:'邮件内容不能为空!'}");
				return "success";
			}
			if (replyBoxId != null && !"".equals(replyBoxId)) {
				MailBox reply = (MailBox) mailBoxService.get(replyBoxId);
				reply.setReplyFlag(Short.valueOf(HAVE_READ));
				mailBoxService.save(reply);
			}
			MailFolder receiveFolder = (MailFolder) mailFolderService.get(Long
					.valueOf(FOLDER_ID_RECEIVE));
			MailFolder sendFolder = (MailFolder) mailFolderService.get(Long
					.valueOf(FOLDER_ID_SEND));
			String recipientIDs[] = mail.getRecipientIDs().split(",");
			String copyToIDs[] = mail.getCopyToIDs().split(",");
			if (mail.getMailId() == null) {
				SaveMail();
				MailBox mailBox = new MailBox();
				mailBox.setMail(mail);
				mailBox.setMailFolder(sendFolder);
				mailBox.setAppUser(ContextUtil.getCurrentUser());
				mailBox.setSendTime(mail.getSendTime());
				mailBox.setDelFlag(Short.valueOf(NOT_DELETE));
				mailBox.setReadFlag(Short.valueOf(NOT_READ));
				mailBox.setNote("已发送的邮件");
				mailBox.setReplyFlag(Short.valueOf(NOT_REPLY));
				mailBoxService.save(mailBox);
			} else {
				Mail old = (Mail) mailService.get(mail.getMailId());
				try {
					BeanUtil.copyNotNullProperties(old, mail);
					Set mailAttachs = new HashSet();
					old.setSendTime(new Date());
					String fileIds[] = mail.getFileIds().split(",");
					String as1[];
					int i1 = (as1 = fileIds).length;
					for (int l = 0; l < i1; l++) {
						String id = as1[l];
						if (!id.equals(""))
							mailAttachs.add((FileAttach) fileAttachService
									.get(new Long(id)));
					}

					old.setMailAttachs(mailAttachs);
					setMail(old);
					mailService.save(old);
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
				MailBox drafted = (MailBox) mailBoxService.get(boxId);
				drafted.setMailFolder(sendFolder);
				drafted.setNote("已发送的邮件");
				mailBoxService.save(drafted);
			}
			if (sendMessage != null && sendMessage.equals("on"))
				shortMessageService.save(ContextUtil.getCurrentUserId(),
						mail.getRecipientIDs(), "您有新邮件!",
						ShortMessage.MSG_TYPE_SYS);
			String as[];
			int k = (as = recipientIDs).length;
			for (int i = 0; i < k; i++) {
				String id = as[i];
				if (!"".equals(id)) {
					MailBox mailBoxSend = new MailBox();
					mailBoxSend.setMail(mail);
					mailBoxSend.setMailFolder(receiveFolder);
					mailBoxSend.setAppUser((AppUser) appUserService
							.get(new Long(id)));
					mailBoxSend.setSendTime(mail.getSendTime());
					mailBoxSend.setDelFlag(Short.valueOf(NOT_DELETE));
					mailBoxSend.setReadFlag(Short.valueOf(NOT_READ));
					mailBoxSend.setNote("发送出去的邮件");
					mailBoxSend.setReplyFlag(Short.valueOf(NOT_REPLY));
					mailBoxService.save(mailBoxSend);
				}
			}

			k = (as = copyToIDs).length;
			for (int j = 0; j < k; j++) {
				String id = as[j];
				if (!"".equals(id)) {
					MailBox mailBoxCopy = new MailBox();
					mailBoxCopy.setMail(mail);
					mailBoxCopy.setMailFolder(receiveFolder);
					mailBoxCopy.setAppUser((AppUser) appUserService
							.get(new Long(id)));
					mailBoxCopy.setSendTime(mail.getSendTime());
					mailBoxCopy.setDelFlag(Short.valueOf(NOT_DELETE));
					mailBoxCopy.setReadFlag(Short.valueOf(NOT_READ));
					mailBoxCopy.setNote("抄送出去的邮件");
					mailBoxCopy.setReplyFlag(Short.valueOf(NOT_REPLY));
					mailBoxService.save(mailBoxCopy);
				}
			}

		} else {
			if (StringUtils.isEmpty(mail.getSubject())) {
				setJsonString("{failure:true,msg:'邮件主题不能为空!'}");
				return "success";
			}
			SaveMail();
			MailFolder draftFolder = (MailFolder) mailFolderService.get(Long
					.valueOf(FOLDER_ID_DRAFT));
			MailBox mailBox = new MailBox();
			mailBox.setMail(mail);
			mailBox.setMailFolder(draftFolder);
			mailBox.setAppUser(ContextUtil.getCurrentUser());
			mailBox.setSendTime(mail.getSendTime());
			mailBox.setDelFlag(Short.valueOf(NOT_DELETE));
			mailBox.setReadFlag(Short.valueOf(NOT_READ));
			mailBox.setNote("存草稿");
			mailBox.setReplyFlag(Short.valueOf(NOT_REPLY));
			mailBoxService.save(mailBox);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public void SaveMail() {
		Set mailAttachs = new HashSet();
		setAppUser(ContextUtil.getCurrentUser());
		mail.setAppSender(appUser);
		mail.setSendTime(new Date());
		mail.setSender(appUser.getFullname());
		String fileIds[] = mail.getFileIds().split(",");
		String as[];
		int j = (as = fileIds).length;
		for (int i = 0; i < j; i++) {
			String id = as[i];
			if (!id.equals(""))
				mailAttachs.add((FileAttach) fileAttachService
						.get(new Long(id)));
		}

		mail.setMailAttachs(mailAttachs);
		mailService.save(mail);
	}

	public String opt() {
		setMail((Mail) mailService.get(mailId));
		String opt = getRequest().getParameter("opt");
		Mail reply = new Mail();
		StringBuffer newSubject = new StringBuffer(
				"<br><br><br><br><br><br><br><hr>");
		newSubject.append((new StringBuilder("<br>----<strong>")).append(opt)
				.append("邮件</strong>----").toString());
		newSubject.append((new StringBuilder("<br><strong>发件人</strong>:"))
				.append(mail.getSender()).toString());
		newSubject.append((new StringBuilder("<br><strong>发送时间</strong>:"))
				.append(mail.getSendTime()).toString());
		newSubject.append((new StringBuilder("<br><strong>收件人</strong>:"))
				.append(mail.getRecipientNames()).toString());
		String copyToNames = mail.getCopyToNames();
		if (!"".equals(copyToNames) && copyToNames != null)
			newSubject.append((new StringBuilder("<br><strong>抄送人</strong>:"))
					.append(copyToNames).toString());
		newSubject.append((new StringBuilder("<br><strong>主题</strong>:"))
				.append(mail.getSubject()).toString());
		newSubject
				.append((new StringBuilder("<br><strong>内容</strong>:<br><br>"))
						.append(mail.getContent()).toString());
		reply.setContent(newSubject.toString());
		reply.setSubject((new StringBuilder(String.valueOf(opt))).append(":")
				.append(mail.getSubject()).toString());
		reply.setImportantFlag(Short.valueOf(COMMON));
		if (opt.equals("回复")) {
			MailBox replyBox = (MailBox) mailBoxService.get(boxId);
			replyBox.setReplyFlag(HAVE_REPLY);
			mailBoxService.save(replyBox);
			reply.setRecipientIDs((new StringBuilder()).append(
					mail.getAppSender().getUserId()).toString());
			reply.setRecipientNames(mail.getSender());
		}
		JSONSerializer serializer = new JSONSerializer();
		StringBuffer sb = new StringBuffer("{success:true,data:[");
		sb.append(serializer.exclude(new String[] { "class", "appUser" })
				.serialize(reply));
		sb.append("]}");
		setJsonString(sb.toString());
		return "success";
	}

	public String move() {
		MailFolder moveToFolder = (MailFolder) mailFolderService.get(new Long(
				folderId.longValue()));
		String ids[] = boxIds.split(",");
		StringBuffer msg = new StringBuffer("{");
		boolean moveSuccess = false;
		if (ids[0] != null && !"".equals(ids[0])) {
			Mail moveTest = ((MailBox) mailBoxService.get(new Long(ids[0])))
					.getMail();
			if (moveTest.getMailStatus().shortValue() == IS_DRAFT) {
				if (folderId.longValue() == FOLDER_ID_DRAFT
						|| folderId.longValue() == FOLDER_ID_DELETE)
					moveSuccess = true;
				else
					msg.append("msg:'草稿只能移至草稿箱或是垃圾箱(移至垃圾箱相当于删除,请注意!)'");
			} else if (moveTest.getMailStatus().shortValue() == IS_MAIL)
				if (folderId.longValue() != FOLDER_ID_DRAFT)
					moveSuccess = true;
				else
					msg.append("msg:'正式邮件不能移至草稿箱'");
		}
		if (moveSuccess) {
			String as[];
			int j = (as = ids).length;
			for (int i = 0; i < j; i++) {
				String id = as[i];
				if (!"".equals(id)) {
					MailBox mailBoxMove = (MailBox) mailBoxService
							.get(new Long(id));
					mailBoxMove.setMailFolder(moveToFolder);
					if (folderId.longValue() != FOLDER_ID_DELETE)
						mailBoxMove.setDelFlag(Short.valueOf(NOT_DELETE));
					else
						mailBoxMove.setDelFlag(Short.valueOf(HAVE_DELETE));
					mailBoxService.save(mailBoxMove);
				}
			}

			msg.append("success:true}");
			setJsonString(msg.toString());
		} else {
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}
		return "success";
	}

	public String attach() {
		String fileIds = getRequest().getParameter("fileIds");
		String filenames = getRequest().getParameter("filenames");
		setMail((Mail) mailService.get(mailId));
		Set mailAttachs = mail.getMailAttachs();
		FileAttach remove = (FileAttach) fileAttachService.get(fileId);
		mailAttachs.remove(remove);
		mail.setMailAttachs(mailAttachs);
		mail.setFileIds(fileIds);
		mail.setFilenames(filenames);
		mailService.save(mail);
		fileAttachService.remove(fileId);
		return "success";
	}

	public String search() {
		PagingBean pb = getInitPagingBean();
		String searchContent = getRequest().getParameter("searchContent");
		List list = mailBoxService.findBySearch(searchContent, pb);
		Gson gson = new Gson();
		StringBuffer buff = (new StringBuffer("{success:true,'totalCounts':"))
				.append(list.size()).append(",result:[");
		for (Iterator iterator = list.iterator(); iterator.hasNext(); buff
				.append("'},")) {
			MailBox mailBoxTemp = (MailBox) iterator.next();
			Mail mailTemp = mailBoxTemp.getMail();
			buff.append("{boxId:'")
					.append(mailBoxTemp.getBoxId())
					.append("',sendTime:'")
					.append(mailBoxTemp.getSendTime())
					.append("',delFlag:'")
					.append(mailBoxTemp.getDelFlag())
					.append("',readFlag:'")
					.append(mailBoxTemp.getReadFlag())
					.append("',replyFlag:'")
					.append(mailBoxTemp.getReplyFlag())
					.append("',mailId:'")
					.append(mailTemp.getMailId())
					.append("',importantFlag:'")
					.append(mailTemp.getImportantFlag())
					.append("',mailStatus:'")
					.append(mailTemp.getMailStatus())
					.append("',fileIds:'")
					.append(mailTemp.getFileIds())
					.append("',subject:'")
					.append(gson.toJson(mailTemp.getSubject())
							.replace("\"", "")).append("',recipientNames:'")
					.append(mailTemp.getRecipientNames()).append("',sender:'")
					.append(mailTemp.getSender()).append("',content:'");
			String content = StringUtil.html2Text(mailTemp.getContent());
			content = gson.toJson(content).replace("\"", "");
			if (content.length() > 100)
				content = (new StringBuilder(String.valueOf(content.substring(
						0, 100)))).append("...").toString();
			buff.append(content);
		}

		if (list.size() > 0)
			buff.deleteCharAt(buff.length() - 1);
		buff.append("]}");
		jsonString = buff.toString();
		return "success";
	}

}