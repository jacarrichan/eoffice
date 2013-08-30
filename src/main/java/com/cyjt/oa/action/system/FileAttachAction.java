package com.cyjt.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.cyjt.core.command.QueryFilter;
import com.cyjt.core.web.action.BaseAction;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.model.system.FileAttach;
import com.cyjt.oa.service.system.FileAttachService;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;

public class FileAttachAction extends BaseAction {

	@Resource
	private FileAttachService fileAttachService;
	private FileAttach fileAttach;
	private Long fileId;
	private String filePath;

	public Long getFileId() {
		return this.fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public FileAttach getFileAttach() {
		return this.fileAttach;
	}

	public void setFileAttach(FileAttach fileAttach) {
		this.fileAttach = fileAttach;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String list() {
		Properties props = new FileAttachAction().readProperty();
		int start = new QueryFilter(getRequest()).getPagingBean().getStart()
				.intValue();
		PagingBean pb = new PagingBean(start, 10);
		String imageOrOthersFile = getRequest().getParameter("type");
		boolean bo = true;
		if ((imageOrOthersFile != null)
				&& (imageOrOthersFile.toLowerCase().equals("image"))) {
			bo = false;
			pb = new PagingBean(start, 8);
		}
		String fileType = getRequest().getParameter("Q_fileType_S_LK");
		if ((fileType == null) || (fileType.equals(""))) {
			fileType = "";
		} else {
			String ft = "";
			if (fileType.indexOf("/") > 0) {
				ft = fileType.substring(0, fileType.length() - 2);
				fileType = getPropertyValue(props, ft) + "/%";
			} else {
				ft = fileType.substring(0, fileType.length() - 1);
				fileType = getPropertyValue(props, ft) + "%";
			}
		}
		List<FileAttach> list = this.fileAttachService.fileList(pb, fileType,
				bo);
		return listToJson(list, pb);
	}

	public String listAll() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("fileType", "DESC");
		List<FileAttach> list = this.fileAttachService.getAll(filter);
		return listToJson(list, filter.getPagingBean());
	}

	public String loadTree() {
		StringBuffer sb = new StringBuffer("[");
		List<FileAttach> fList = searchOneNodes();
		Properties props = new FileAttachAction().readProperty();
		for (int i = 0; i < fList.size(); i++) {
			String s = (fList.get(i)).getFileType();
			String fStr = "{id:'" + (fList.get(i)).getFileId() + "',text:'"
					+ getPropertyValue(props, s) + "'";

			List<FileAttach> zList = searchTwoNodes(s);
			if (zList.size() > 0) {
				fStr = fStr + ",children:[";
				for (int j = 0; j < zList.size(); j++) {
					String z = (zList.get(j)).getFileType();
					String zStr = "{id:'" + (zList.get(j)).getFileId()
							+ "',text:'" + getPropertyValue(props, z) + "'";

					List<FileAttach> sList = searchThreeNodes(s + "/" + z);
					if (sList.size() > 0) {
						zStr = zStr + ",children:[";
						for (int k = 0; k < sList.size(); k++) {
							String sz = (sList.get(k)).getFileType();
							String szStr = "{id:'" + (sList.get(k)).getFileId()
									+ "',text:'" + getPropertyValue(props, sz)
									+ "',leaf:true},";
							if (k == sList.size() - 1)
								szStr = szStr.substring(0, szStr.length() - 1);
							zStr = zStr + szStr;
						}
						zStr = zStr + "]},";
					} else {
						zStr = zStr + ",leaf:true},";
					}
					if (j == zList.size() - 1)
						zStr = zStr.substring(0, zStr.length() - 1);
					fStr = fStr + zStr;
				}
				fStr = fStr + "]},";
			} else {
				fStr = fStr + ",leaf:true},";
			}
			if (i == fList.size() - 1)
				fStr = fStr.substring(0, fStr.length() - 1);
			sb.append(fStr);
		}

		setJsonString("]");
		return "success";
	}

	public String multiDel() {
		String ids = getRequest().getParameter("ids");
		if (ids != null) {
			for (String id : ids.split(",")) {
				this.fileAttachService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		FileAttach fileAttach = this.fileAttachService.get(this.fileId);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(fileAttach));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		this.fileAttachService.save(this.fileAttach);
		setJsonString("{success:true}");
		return "success";
	}

	@Override
	public String delete() {
		this.fileAttachService.removeByPath(this.filePath);
		return "success";
	}

	private String listToJson(List<FileAttach> list, PagingBean pb) {
		Type type = new TypeToken<List<FileAttach>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	private List<FileAttach> searchOneNodes() {
		List<FileAttach> list = this.fileAttachService.getAll();
		List<FileAttach> newList = new ArrayList<FileAttach>();

		Iterator<FileAttach> it = list.iterator();
		while (it.hasNext()) {
			FileAttach f = it.next();
			String fileType = f.getFileType();
			if (fileType.indexOf("/") > 0)
				f.setFileType(fileType.substring(0, fileType.indexOf("/")));
			else
				f.setFileType(fileType);
			if (newList.size() > 0) {
				if (judgeDBRepeat(f.getFileType(), newList))
					newList.add(f);
			} else
				newList.add(f);
		}
		list.clear();
		return newList;
	}

	private boolean judgeDBRepeat(String str, List<FileAttach> list) {
		boolean bo = true;
		Iterator<FileAttach> it = list.iterator();
		while (it.hasNext()) {
			FileAttach f = it.next();
			if (f.getFileType().equals(str))
				bo = false;
		}
		return bo;
	}

	private List<FileAttach> searchTwoNodes(String ft) {
		List<FileAttach> list = this.fileAttachService.fileList(ft + "/%");
		List<FileAttach> newList = new ArrayList<FileAttach>();

		Iterator<FileAttach> it = list.iterator();
		while (it.hasNext()) {
			FileAttach f = it.next();
			String fileType = f.getFileType();
			f.setFileType(fileType.substring(ft.length() + 1, fileType.length()));
			if (newList.size() > 0) {
				if (judgeDBRepeat(fileType, newList))
					newList.add(f);
			} else
				newList.add(f);
		}
		list.clear();
		return newList;
	}

	private List<FileAttach> searchThreeNodes(String ft) {
		List<FileAttach> list = this.fileAttachService.fileList(ft + "/");
		List<FileAttach> newList = new ArrayList<FileAttach>();

		Iterator<FileAttach> it = list.iterator();
		while (it.hasNext()) {
			FileAttach f = it.next();
			String fileType = f.getFileType();
			f.setFileType(fileType.substring(ft.length() + 1, fileType.length()));
			if (newList.size() > 0) {
				if (judgeDBRepeat(fileType, newList))
					newList.add(f);
			} else
				newList.add(f);
		}
		list.clear();
		return newList;
	}

	private Properties readProperty() {
		Properties props = new Properties();
		String filePath = getSession().getServletContext().getRealPath(
				"/WEB-INF/classes");
		try {
			FileInputStream fis = new FileInputStream(filePath
					+ "/upload-directory.properties");
			Reader r = new InputStreamReader(fis, "UTF-8");
			props.load(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	private String getPropertyValue(Properties props, String s) {
		if (props.getProperty(s) == null) {
			return s;
		}
		return props.getProperty(s);
	}
}
