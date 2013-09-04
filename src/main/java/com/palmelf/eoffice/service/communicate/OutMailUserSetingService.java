package com.palmelf.eoffice.service.communicate;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.communicate.OutMailUserSeting;

public abstract interface OutMailUserSetingService extends
		BaseService<OutMailUserSeting> {
	public abstract OutMailUserSeting getByLoginId(Long paramLong);
}
