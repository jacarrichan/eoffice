package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.communicate.MailFolder;

import java.util.List;

public abstract interface MailFolderService extends BaseService<MailFolder> {
	public abstract List<MailFolder> getUserFolderByParentId(Long paramLong1,
			Long paramLong2);

	public abstract List<MailFolder> getAllUserFolderByParentId(
			Long paramLong1, Long paramLong2);

	public abstract List<MailFolder> getFolderLikePath(String paramString);
}
