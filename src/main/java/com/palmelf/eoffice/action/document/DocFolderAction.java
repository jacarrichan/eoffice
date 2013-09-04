package com.palmelf.eoffice.action.document;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.document.DocFolder;
import com.palmelf.eoffice.model.document.DocPrivilege;
import com.palmelf.eoffice.model.document.Document;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.document.DocFolderService;
import com.palmelf.eoffice.service.document.DocPrivilegeService;
import com.palmelf.eoffice.service.document.DocumentService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

public class DocFolderAction extends BaseAction {

	@Resource
	private DocFolderService docFolderService;

	@Resource
	private DocPrivilegeService docPrivilegeService;

	@Resource
	private DocumentService documentService;
	private DocFolder docFolder;
	private Long folderId;
	private static Integer ALL_RIGHT = Integer.valueOf(7);
	private static Integer NOT_RIGHT = Integer.valueOf(0);
	private static Long ISPARENT = Long.valueOf(0L);

	public Long getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public DocFolder getDocFolder() {
		return this.docFolder;
	}

	public void setDocFolder(DocFolder docFolder) {
		this.docFolder = docFolder;
	}

	@Override
	public String list() {
		String method = getRequest().getParameter("method");
		StringBuffer buff = new StringBuffer();
		boolean flag = false;
		if (StringUtils.isNotEmpty(method)) {
			buff.append("[");
			flag = true;
		} else {
			buff.append("[{id:'0',text:'我的文件夹',expanded:true,children:[");
		}
		Long curUserId = ContextUtil.getCurrentUserId();
		List<DocFolder> docList = this.docFolderService
				.getUserFolderByParentId(curUserId, Long.valueOf(0L));
		for (DocFolder folder : docList) {
			buff.append("{id:'" + folder.getFolderId())
					.append("',text:'" + folder.getFolderName()).append("',");
			buff.append(findChildsFolder(curUserId, folder.getFolderId()));
		}
		if (!docList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (flag)
			buff.append("]");
		else {
			buff.append("]}]");
		}
		setJsonString(buff.toString());
		this.logger.info("tree json:" + buff.toString());
		return "success";
	}

	public String tree() {
		StringBuffer buff = new StringBuffer(
				"[{id:'0',text:'公共文件夹',expanded:true,children:[");
		List<DocFolder> docList = this.docFolderService
				.getPublicFolderByParentId(Long.valueOf(0L));
		for (DocFolder folder : docList) {
			buff.append("{id:'" + folder.getFolderId())
					.append("',text:'" + folder.getFolderName()).append("',");
			buff.append(findChildsFolder(folder.getFolderId()));
		}
		if (!docList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());

		this.logger.info("tree json:" + buff.toString());
		return "success";
	}

	public String select() {
		AppUser appUser = ContextUtil.getCurrentUser();
		StringBuffer buff = new StringBuffer(
				"[{id:'0',text:'公共文件夹',expanded:true,children:[");
		List<DocFolder> docList = this.docFolderService
				.getPublicFolderByParentId(Long.valueOf(0L));
		for (DocFolder docFolder : docList) {
			List<Integer> rights = this.docPrivilegeService.getRightsByFolder(
					appUser, docFolder.getFolderId());
			Integer right = NOT_RIGHT;
			for (Integer in : rights) {
				right = Integer.valueOf(right.intValue() | in.intValue());
			}
			Set<String> roleRight = appUser.getRights();
			if (roleRight.contains("__ALL")) {
				right = ALL_RIGHT;
			}
			if (right == NOT_RIGHT) {
				buff.append("{id:'" + docFolder.getFolderId())
						.append("',disabled:true,text:'"
								+ docFolder.getFolderName()).append("',");
				buff.append(findChildsFolderByRight(docFolder.getFolderId(),
						right, false));
			} else {
				buff.append("{id:'" + docFolder.getFolderId())
						.append("',text:'" + docFolder.getFolderName())
						.append("',");
				if (right == ALL_RIGHT)
					buff.append(findChildsFolderByRight(
							docFolder.getFolderId(), right, true));
				else {
					buff.append(findChildsFolderByRight(
							docFolder.getFolderId(), right, false));
				}
			}
		}
		if (!docList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());
		return "success";
	}

	public String share() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_isShared_SN_EQ", "1");
		List<DocFolder> list = this.docFolderService.getAll(filter);
		Type type = new TypeToken<List<DocFolder>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String findChildsFolder(Long userId, Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = this.docFolderService.getUserFolderByParentId(
				userId, parentId);
		if (list.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		}
		sb.append("children:[");
		for (DocFolder folder : list) {
			sb.append("{id:'" + folder.getFolderId() + "',text:'"
					+ folder.getFolderName() + "',");
			sb.append(findChildsFolder(userId, folder.getFolderId()));
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]},");
		return sb.toString();
	}

	public String findChildsFolder(Long parentId) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = this.docFolderService
				.getPublicFolderByParentId(parentId);
		if (list.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		}
		sb.append("children:[");
		for (DocFolder folder : list) {
			sb.append("{id:'" + folder.getFolderId() + "',text:'"
					+ folder.getFolderName() + "',");
			sb.append(findChildsFolder(folder.getFolderId()));
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]},");
		return sb.toString();
	}

	public String findChildsFolderByRight(Long parentId, Integer right,
			boolean isAllRight) {
		StringBuffer sb = new StringBuffer();
		List<DocFolder> list = this.docFolderService
				.getPublicFolderByParentId(parentId);
		if (list.size() == 0) {
			sb.append("leaf:true,expanded:true},");
			return sb.toString();
		}
		sb.append("children:[");
		for (DocFolder folder : list) {
			Integer in = right;
			if (isAllRight) {
				in = ALL_RIGHT;
			} else if (in != NOT_RIGHT) {
				in = NOT_RIGHT;
				AppUser appUser = ContextUtil.getCurrentUser();
				List<Integer> rights = this.docPrivilegeService
						.getRightsByFolder(appUser, folder.getFolderId());
				for (Integer inte : rights) {
					in = Integer.valueOf(in.intValue() | inte.intValue());
				}
			}

			if (in == NOT_RIGHT) {
				sb.append("{id:'" + folder.getFolderId()
						+ "',disabled:true,text:'" + folder.getFolderName()
						+ "',");
				sb.append(findChildsFolderByRight(folder.getFolderId(), in,
						isAllRight));
			} else {
				sb.append("{id:'" + folder.getFolderId() + "',text:'"
						+ folder.getFolderName() + "',");
				sb.append(findChildsFolderByRight(folder.getFolderId(), in,
						isAllRight));
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]},");
		return sb.toString();
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.docFolderService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String remove() {
		String folderId = getRequest().getParameter("folderId");
		if (StringUtils.isNotEmpty(folderId)) {
			DocFolder tmpFolder = this.docFolderService.get(new Long(folderId));
			List<DocFolder> docFolderList = this.docFolderService
					.getFolderLikePath(tmpFolder.getPath());

			for (DocFolder folder : docFolderList) {
				List<Document> list = this.documentService.findByFolder(folder
						.getPath());
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'该目录下还有文档，请把文件删除后删除该目录'}";
					return "success";
				}
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_docFolder.folderId_L_EQ", folder
						.getFolderId().toString());
				List<DocPrivilege> priList = this.docPrivilegeService
						.getAll(filter);
				for (DocPrivilege dp : priList) {
					this.docPrivilegeService.remove(dp);
				}
				this.docFolderService.remove(folder.getFolderId());
			}
		}

		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		DocFolder docFolder = this.docFolderService.get(this.folderId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(docFolder));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		if (this.docFolder.getFolderId() == null) {
			if (this.docFolder.getIsShared() != null) {
				this.docFolder.setIsShared(DocFolder.IS_SHARED);
			} else {
				this.docFolder.setAppUser(ContextUtil.getCurrentUser());
				this.docFolder.setIsShared(DocFolder.IS_NOT_SHARED);
			}
			this.docFolderService.save(this.docFolder);

			if ((this.docFolder.getParentId() == null)
					|| (this.docFolder.getParentId().longValue() == 0L)) {
				this.docFolder.setPath(this.docFolder.getFolderId() + ".");
			} else {
				DocFolder pFolder = this.docFolderService.get(this.docFolder
						.getParentId());
				if (pFolder != null) {
					this.docFolder.setPath(pFolder.getPath()
							+ this.docFolder.getFolderId() + ".");
				}
			}

			this.docFolderService.save(this.docFolder);
		} else {
			DocFolder df = this.docFolderService.get(this.docFolder
					.getFolderId());

			df.setFolderName(this.docFolder.getFolderName());
			this.docFolderService.save(df);
		}

		setJsonString("{success:true}");
		return "success";
	}

	public String move() {
		String strFolderIdOld = getRequest().getParameter("folderIdOld");
		String strFolderIdNew = getRequest().getParameter("folderIdNew");
		if ((StringUtils.isNotEmpty(strFolderIdOld))
				&& (StringUtils.isNotEmpty(strFolderIdNew))) {
			Long folderIdOld = new Long(strFolderIdOld);
			Long folderIdNew = new Long(strFolderIdNew);
			String newPath = null;
			DocFolder folderOld = this.docFolderService.get(folderIdOld);
			DocFolder folderNew = new DocFolder();
			if (folderIdNew.longValue() > 0L) {
				folderNew = this.docFolderService.get(folderIdNew);
				newPath = folderNew.getPath() + folderIdOld.toString() + ".";
				boolean flag = Pattern.compile(folderOld.getPath())
						.matcher(folderNew.getPath()).find();
				if (flag) {
					setJsonString("{success:false,msg:'不能移到子文件夹下！'}");
					return "success";
				}
			} else {
				folderIdNew = ISPARENT;
				newPath = folderIdOld.toString() + ".";
			}
			String oldPath = folderOld.getPath();
			folderOld.setParentId(folderIdNew);
			folderOld.setPath(newPath);
			List<DocFolder> list = this.docFolderService
					.getFolderLikePath(oldPath);
			for (DocFolder folder : list) {
				folder.setPath(folder.getPath().replaceFirst(oldPath, newPath));
				this.docFolderService.save(folder);
			}
			this.docFolderService.save(folderOld);
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false,msg:'请联系系统管理员！'}");
		}
		return "success";
	}
}
