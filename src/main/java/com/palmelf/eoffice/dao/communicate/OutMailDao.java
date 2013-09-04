package com.palmelf.eoffice.dao.communicate;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.communicate.OutMail;

import java.util.List;
import java.util.Map;

public abstract interface OutMailDao extends BaseDao<OutMail> {
	public abstract List<OutMail> findByFolderId(Long paramLong);

	public abstract Long CountByFolderId(Long paramLong);

	public abstract Map getUidByUserId(Long paramLong);
}
