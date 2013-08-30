package com.cyjt.oa.service.document;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.document.DocFolder;
import java.util.List;

public abstract interface DocFolderService extends BaseService<DocFolder> {
	public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1,
			Long paramLong2);

	public abstract List<DocFolder> getFolderLikePath(String paramString);

	public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);
}
