package com.cyjt.oa.action.communicate;

import com.google.gson.Gson;
import com.cyjt.core.util.ContextUtil;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.oa.model.communicate.OutMail;
import com.cyjt.oa.model.communicate.OutMailFolder;
import com.cyjt.oa.service.communicate.OutMailFolderService;
import com.cyjt.oa.service.communicate.OutMailService;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

public class OutMailFolderAction extends BaseAction {
	static long FOLDER_ID_RECEIVE = 1L;
	static long FOLDER_ID_SEND = 2L;
	static long FOLDER_ID_DRAFT = 3L;
	static long FOLDER_ID_DELETE = 4L;
	static long FOLDER_TYPE_OTHER = 10L;
	static short OTHER_FOLDER_TYPE = 10;

	static int FIRST_LEVEL = 1;
	static long FIRST_PARENTID = new Long(0L).longValue();

	@Resource
	private OutMailFolderService outMailFolderService;
	private OutMailFolder outMailFolder;

	@Resource
	private OutMailService outMailService;
	private OutMail outMail;
	private Long folderId;

	public Long getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public OutMailFolder getOutMailFolder() {
		return this.outMailFolder;
	}

	public void setOutMailFolder(OutMailFolder outMailFolder) {
		this.outMailFolder = outMailFolder;
	}

	@Override
	public String list() {
		StringBuffer buff = new StringBuffer(
				"[{id:'0',text:'外部邮箱',iconCls:'menu-mail_box',expanded:true,children:[");
		Long curUserId = ContextUtil.getCurrentUserId();
		List<OutMailFolder> outMailFolderList = this.outMailFolderService
				.getAllUserFolderByParentId(curUserId,
						Long.valueOf(FIRST_PARENTID));

		for (OutMailFolder folder : outMailFolderList) {
			long folderType = folder.getFolderId().longValue();

			buff.append("{id:'" + folder.getFolderId())
					.append("',text:'" + folder.getFolderName()).append("',");

			if (folderType == FOLDER_ID_RECEIVE)
				buff.append("iconCls:'menu-mail_inbox',");
			else if (folderType == FOLDER_ID_SEND)
				buff.append("iconCls:'menu-mail_outbox',");
			else if (folderType == FOLDER_ID_DRAFT)
				buff.append("iconCls:'menu-mail_drafts',");
			else if (folderType == FOLDER_ID_DELETE)
				buff.append("iconCls:'menu-mail_trash',");
			else {
				buff.append("iconCls:'menu-mail_folder',");
			}
			buff.append(findChildsFolder(curUserId, folder.getFolderId()));
		}

		if (!outMailFolderList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());

		return "success";
	}

	private String findChildsFolder(Long userId, Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<OutMailFolder> folders = this.outMailFolderService
				.getUserFolderByParentId(userId, parentId);
		if (folders.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		}
		sb.append("children:[");
		for (OutMailFolder folder : folders) {
			sb.append("{id:'" + folder.getFolderId() + "',text:'"
					+ folder.getFolderName() + "',");
			sb.append("iconCls:'menu-mail_folder',");
			sb.append(findChildsFolder(userId, folder.getFolderId()));
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]},");
		return sb.toString();
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.outMailFolderService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		OutMailFolder outMailFolder = this.outMailFolderService
				.get(this.folderId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(outMailFolder));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		OutMailFolder parentFolder = null;
		System.out.println("outMailFolder=" + this.outMailFolder);
		Long parentId = this.outMailFolder.getParentId();
		System.out.println(parentId);
		if ((parentId == null) || (parentId.longValue() == FIRST_PARENTID)) {
			this.outMailFolder.setParentId(new Long(FIRST_PARENTID));
			this.outMailFolder.setDepLevel(Integer.valueOf(FIRST_LEVEL));
			System.out.println("11111outMailFolder" + this.outMailFolder);
		} else {
			parentFolder = this.outMailFolderService.get(parentId);
			this.outMailFolder.setDepLevel(Integer.valueOf(parentFolder
					.getDepLevel().intValue() + 1));
			System.out.println("2222outMailFolder" + this.outMailFolder);
			System.out.println("2222parentFolder" + parentFolder);
		}
		this.outMailFolder.setFolderType(Short.valueOf(OTHER_FOLDER_TYPE));
		this.outMailFolder.setUserId(ContextUtil.getCurrentUserId());
		this.outMailFolderService.save(this.outMailFolder);

		if (this.outMailFolder.getParentId().longValue() == FIRST_PARENTID)
			this.outMailFolder.setPath("0." + this.outMailFolder.getFolderId()
					+ ".");
		else {
			this.outMailFolder.setPath(parentFolder.getPath()
					+ this.outMailFolder.getFolderId() + ".");
		}
		this.outMailFolderService.save(this.outMailFolder);
		setJsonString("{success:true}");
		return "success";
	}

	public String remove() {
		String count = getRequest().getParameter("count");
		if (this.folderId != null) {
			OutMailFolder tmpFolder = this.outMailFolderService.get(new Long(
					this.folderId.longValue()));

			List<OutMailFolder> outMailFolderList = this.outMailFolderService
					.getFolderLikePath(tmpFolder.getPath());
			if ((count != null) && (new Long(count).longValue() > 0L)) {
				OutMailFolder deleteFolder = this.outMailFolderService
						.get(new Long(FOLDER_ID_DELETE));
				for (Iterator localIterator1 = outMailFolderList.iterator(); localIterator1
						.hasNext();) {
					OutMailFolder folder = (OutMailFolder) localIterator1
							.next();
					List<OutMail> outMailList = this.outMailService
							.findByFolderId(folder.getFolderId());
					for (OutMail outMail : outMailList) {
						outMail.setOutMailFolder(deleteFolder);
						this.outMailService.save(outMail);
					}
				}

			}

			for (OutMailFolder folder : outMailFolderList) {
				this.outMailFolderService.remove(folder.getFolderId());
			}
		}

		this.jsonString = "{success:true}";
		return "success";
	}

	public String count() {
		OutMailFolder tmpFolder = this.outMailFolderService.get(new Long(
				this.folderId.longValue()));
		List<OutMailFolder> outMailFolderList = this.outMailFolderService
				.getFolderLikePath(tmpFolder.getPath());

		Long total = Long.valueOf(0L);
		for (OutMailFolder folder : outMailFolderList) {
			Long count = this.outMailService.CountByFolderId(folder
					.getFolderId());
			total = Long.valueOf(total.longValue() + count.longValue());
		}

		setJsonString("{success:true,count:" + total + "}");
		return "success";
	}
}
