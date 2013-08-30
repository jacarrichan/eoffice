package com.cyjt.oa.dao.document;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.document.DocFolder;
import java.util.List;

public abstract interface DocFolderDao extends BaseDao<DocFolder> {
	public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1,
			Long paramLong2);

	public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);

	public abstract List<DocFolder> getFolderLikePath(String paramString);
}
