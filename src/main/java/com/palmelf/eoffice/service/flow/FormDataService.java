package com.palmelf.eoffice.service.flow;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.flow.FormData;

import java.util.Map;

public abstract interface FormDataService extends BaseService<FormData> {
	public abstract Map<String, Object> getFromDataMap(Long paramLong,
			String paramString);
}
