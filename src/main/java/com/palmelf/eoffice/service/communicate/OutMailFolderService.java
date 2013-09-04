package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.communicate.OutMailFolder;

import java.util.List;

public abstract interface OutMailFolderService extends
		BaseService<OutMailFolder> {
	public abstract List<OutMailFolder> getAllUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<OutMailFolder> getUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<OutMailFolder> getFolderLikePath(String paramString);
}
