package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.FileAttach;

import java.util.List;

public abstract interface FileAttachService extends BaseService<FileAttach> {
	public abstract void removeByPath(String paramString);

	public abstract FileAttach getByPath(String paramString);

	public abstract void mutilDel(String paramString);

	public abstract List<FileAttach> fileList(PagingBean paramPagingBean,
			String paramString, boolean paramBoolean);

	public abstract List<FileAttach> fileList(String paramString);
}
