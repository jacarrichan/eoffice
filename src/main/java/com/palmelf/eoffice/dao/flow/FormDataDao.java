package com.palmelf.eoffice.dao.flow;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.flow.FormData;

import java.util.List;

public abstract interface FormDataDao extends BaseDao<FormData> {
	public abstract List<FormData> getByRunIdActivityName(Long paramLong,
			String paramString);

	public abstract FormData getByFormIdFieldName(Long paramLong,
			String paramString);
}
