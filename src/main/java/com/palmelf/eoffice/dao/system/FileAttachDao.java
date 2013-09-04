package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.model.system.FileAttach;

import java.util.List;

public abstract interface FileAttachDao extends BaseDao<FileAttach> {
	public abstract void removeByPath(String paramString);

	public abstract FileAttach getByPath(String paramString);

	public abstract List<FileAttach> fileList(PagingBean paramPagingBean,
			String paramString, boolean paramBoolean);

	public abstract List<FileAttach> fileList(String paramString);
}
