package com.palmelf.eoffice.dao.communicate;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.communicate.OutMailUserSeting;

public abstract interface OutMailUserSetingDao extends
		BaseDao<OutMailUserSeting> {
	public abstract OutMailUserSeting getByLoginId(Long paramLong);
}
