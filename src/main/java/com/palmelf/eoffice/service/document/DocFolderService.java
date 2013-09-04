package com.palmelf.eoffice.service.document;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.document.DocFolder;

import java.util.List;

public abstract interface DocFolderService extends BaseService<DocFolder> {
	public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1,
			Long paramLong2);

	public abstract List<DocFolder> getFolderLikePath(String paramString);

	public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);
}
