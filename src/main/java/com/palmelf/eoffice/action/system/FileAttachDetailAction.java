package com.palmelf.eoffice.action.system;

import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.system.FileAttachService;

import javax.annotation.Resource;

public class FileAttachDetailAction extends BaseAction {

	@Resource
	private FileAttachService fileAttachService;
	private Long fileId;
	private FileAttach fileAttach;

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

	@Override
	public String execute() throws Exception {
		this.fileAttach = (this.fileAttachService.get(this.fileId));
		return "success";
	}
}
