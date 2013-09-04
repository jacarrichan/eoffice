package com.palmelf.eoffice.dao.document;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.document.DocFolder;

import java.util.List;

public abstract interface DocFolderDao extends BaseDao<DocFolder> {
	public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1,
			Long paramLong2);

	public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);

	public abstract List<DocFolder> getFolderLikePath(String paramString);
}
