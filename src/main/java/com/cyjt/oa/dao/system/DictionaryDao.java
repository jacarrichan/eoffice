package com.cyjt.oa.dao.system;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.system.Dictionary;
import java.util.List;

public abstract interface DictionaryDao extends BaseDao<Dictionary> {
	public abstract List<String> getAllItems();

	public abstract List<String> getAllByItemName(String paramString);

	public abstract List<Dictionary> getByItemName(String paramString);
}
