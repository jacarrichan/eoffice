package com.cyjt.oa.service.flow;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.flow.FormData;
import java.util.Map;

public abstract interface FormDataService extends BaseService<FormData> {
	public abstract Map<String, Object> getFromDataMap(Long paramLong,
			String paramString);
}
