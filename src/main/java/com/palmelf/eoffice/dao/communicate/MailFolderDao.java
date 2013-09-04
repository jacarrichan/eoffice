package com.palmelf.eoffice.dao.communicate;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.communicate.MailFolder;

import java.util.List;

public abstract interface MailFolderDao extends BaseDao<MailFolder> {
	public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1,
			Long paramLong2);

	public abstract List<MailFolder> getAllUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<MailFolder> getFolderLikePath(String paramString);
}
