package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.communicate.OutMail;

import java.util.List;
import java.util.Map;

public abstract interface OutMailService extends BaseService<OutMail> {
	public abstract List<OutMail> findByFolderId(Long paramLong);

	public abstract Long CountByFolderId(Long paramLong);

	public abstract Map getUidByUserId(Long paramLong);
}
