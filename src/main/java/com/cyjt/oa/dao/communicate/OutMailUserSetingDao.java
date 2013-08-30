package com.cyjt.oa.dao.communicate;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.communicate.OutMailUserSeting;

public abstract interface OutMailUserSetingDao extends
		BaseDao<OutMailUserSeting> {
	public abstract OutMailUserSeting getByLoginId(Long paramLong);
}
