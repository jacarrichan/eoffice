package com.cyjt.oa.dao.communicate;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.communicate.OutMail;
import java.util.List;
import java.util.Map;

public abstract interface OutMailDao extends BaseDao<OutMail> {
	public abstract List<OutMail> findByFolderId(Long paramLong);

	public abstract Long CountByFolderId(Long paramLong);

	public abstract Map getUidByUserId(Long paramLong);
}
