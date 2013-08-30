package com.cyjt.oa.dao.flow;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.flow.FormData;
import java.util.List;

public abstract interface FormDataDao extends BaseDao<FormData> {
	public abstract List<FormData> getByRunIdActivityName(Long paramLong,
			String paramString);

	public abstract FormData getByFormIdFieldName(Long paramLong,
			String paramString);
}
