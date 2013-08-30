package com.cyjt.oa.service.communicate;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.communicate.OutMailUserSeting;

public abstract interface OutMailUserSetingService extends
		BaseService<OutMailUserSeting> {
	public abstract OutMailUserSeting getByLoginId(Long paramLong);
}
