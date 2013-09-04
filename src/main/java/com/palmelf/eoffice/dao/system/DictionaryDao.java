package com.palmelf.eoffice.dao.system;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.system.Dictionary;

import java.util.List;

public abstract interface DictionaryDao extends BaseDao<Dictionary> {
	public abstract List<String> getAllItems();

	public abstract List<String> getAllByItemName(String paramString);

	public abstract List<Dictionary> getByItemName(String paramString);
}
