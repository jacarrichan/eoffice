package com.cyjt.oa.action.communicate;

import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.communicate.MailBox;
import com.cyjt.oa.model.communicate.MailFolder;
import com.cyjt.oa.service.communicate.MailBoxService;
import com.cyjt.oa.service.communicate.MailFolderService;

public class MailFolderAction extends BaseAction {
	static long FOLDER_ID_RECEIVE = 1L;
	static long FOLDER_ID_SEND = 2L;
	static long FOLDER_ID_DRAFT = 3L;
	static long FOLDER_ID_DELETE = 4L;

	static short OTHER_FOLDER_TYPE = 10;
	static int FIRST_LEVEL = 1;
	static long FIRST_LEVEL_PARENTID = 0L;

	@Resource
	private MailFolderService mailFolderService;

	@Resource
	private MailBoxService mailBoxService;
	private MailFolder mailFolder;
	private Long folderId;

	public Long getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public MailFolder getMailFolder() {
		return this.mailFolder;
	}

	public void setMailFolder(MailFolder mailFolder) {
		this.mailFolder = mailFolder;
	}

	@Override
	public String list() {
		StringBuffer buff = new StringBuffer(
				"[{id:'0',text:'我的邮箱',iconCls:'menu-mail_box',expanded:true,children:[");
		Long curUserId = ContextUtil.getCurrentUserId();
		List<MailFolder> mailFolderList = this.mailFolderService
				.getAllUserFolderByParentId(curUserId, Long.valueOf(0L));
		for (MailFolder folder : mailFolderList) {
			buff.append("{id:'" + folder.getFolderId())
					.append("',text:'" + folder.getFolderName()).append("',");
			Long folderId = folder.getFolderId();
			if (folderId.longValue() == MailFolderAction.FOLDER_ID_RECEIVE) {
				buff.append("iconCls:'menu-mail_inbox',");
			} else if (folderId.longValue() == MailFolderAction.FOLDER_ID_SEND) {
				buff.append("iconCls:'menu-mail_outbox',");
			} else if (folderId.longValue() == MailFolderAction.FOLDER_ID_DRAFT) {
				buff.append("iconCls:'menu-mail_drafts',");
			} else if (folderId.longValue() == MailFolderAction.FOLDER_ID_DELETE) {
				buff.append("iconCls:'menu-mail_trash',");
			} else {
				buff.append("iconCls:'menu-mail_folder',");
			}
			buff.append(this.findChildsFolder(curUserId, folder.getFolderId()));
		}
		if (!mailFolderList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		this.setJsonString(buff.toString());

		this.logger.info("tree json:" + buff.toString());

		return "success";
	}

	public String findChildsFolder(Long userId, Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<MailFolder> folders = this.mailFolderService
				.getUserFolderByParentId(userId, parentId);
		if (folders.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		}
		sb.append("children:[");
		for (MailFolder folder : folders) {
			sb.append("{id:'" + folder.getFolderId() + "',text:'"
					+ folder.getFolderName() + "',");
			sb.append("iconCls:'menu-mail_folder',");
			sb.append(this.findChildsFolder(userId, folder.getFolderId()));
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]},");
		return sb.toString();
	}

	public String multiDel() {
		String[] ids = this.getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.mailFolderService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String count() {
		MailFolder tmpFolder = this.mailFolderService.get(new Long(
				this.folderId.longValue()));
		List<MailFolder> mailFolderList = this.mailFolderService
				.getFolderLikePath(tmpFolder.getPath());

		Long total = Long.valueOf(0L);
		for (MailFolder folder : mailFolderList) {
			Long count = this.mailBoxService.CountByFolderId(folder
					.getFolderId());
			total = Long.valueOf(total.longValue() + count.longValue());
		}

		this.setJsonString("{success:true,count:" + total + "}");
		return "success";
	}

	public String remove() {
		String count = this.getRequest().getParameter("count");
		if (this.folderId != null) {
			MailFolder tmpFolder = this.mailFolderService.get(new Long(
					this.folderId.longValue()));
			List<MailFolder> mailFolderList = this.mailFolderService
					.getFolderLikePath(tmpFolder.getPath());
			MailFolder folder;
			if ((count != null) && (new Long(count).longValue() > 0L)) {
				MailFolder deleteFolder = this.mailFolderService.get(Long
						.valueOf(4L));
				for (Object element : mailFolderList) {
					folder = (MailFolder) element;
					List<MailBox> mailBoxList = this.mailBoxService
							.findByFolderId(folder.getFolderId());
					for (MailBox mailBox : mailBoxList) {
						mailBox.setMailFolder(deleteFolder);
						this.mailBoxService.save(mailBox);
					}
				}

			}

			for (MailFolder folder1 : mailFolderList) {
				this.mailFolderService.remove(folder1.getFolderId());
			}
		}

		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		MailFolder mailFolder = this.mailFolderService.get(this.folderId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(mailFolder));
		sb.append("}");
		this.setJsonString(sb.toString());
		return "success";
	}

	@Override
	public String save() {
		MailFolder parentFolder = null;
		Long parentId = this.mailFolder.getParentId();
		if ((parentId == null)
				|| (parentId.longValue() == MailFolderAction.FIRST_LEVEL_PARENTID)) {
			this.mailFolder.setParentId(new Long(
					MailFolderAction.FIRST_LEVEL_PARENTID));
			this.mailFolder.setDepLevel(Integer
					.valueOf(MailFolderAction.FIRST_LEVEL));
		} else {
			parentFolder = this.mailFolderService.get(parentId);
			this.mailFolder.setDepLevel(Integer.valueOf(parentFolder
					.getDepLevel().intValue() + 1));
		}
		this.mailFolder.setFolderType(Short
				.valueOf(MailFolderAction.OTHER_FOLDER_TYPE));
		this.mailFolder.setUserId(ContextUtil.getCurrentUserId());
		this.mailFolderService.save(this.mailFolder);

		if (this.mailFolder.getParentId().longValue() == MailFolderAction.FIRST_LEVEL_PARENTID) {
			this.mailFolder.setPath("0." + this.mailFolder.getFolderId() + ".");
		} else {
			this.mailFolder.setPath(parentFolder.getPath()
					+ this.mailFolder.getFolderId() + ".");
		}
		this.mailFolderService.save(this.mailFolder);
		this.setJsonString("{success:true}");
		return "success";
	}
}
