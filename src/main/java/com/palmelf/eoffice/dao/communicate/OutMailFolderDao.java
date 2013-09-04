package com.palmelf.eoffice.dao.communicate;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.communicate.OutMailFolder;

import java.util.List;

public abstract interface OutMailFolderDao extends BaseDao<OutMailFolder> {
	public abstract List<OutMailFolder> getAllUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<OutMailFolder> getUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<OutMailFolder> getFolderLikePath(String paramString);
}
