package com.cyjt.oa.service.communicate;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.communicate.OutMailFolder;
import java.util.List;

public abstract interface OutMailFolderService extends
		BaseService<OutMailFolder> {
	public abstract List<OutMailFolder> getAllUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<OutMailFolder> getUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<OutMailFolder> getFolderLikePath(String paramString);
}
