package com.cyjt.oa.service.system;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.system.Dictionary;
import java.util.List;

public abstract interface DictionaryService extends BaseService<Dictionary> {
	public abstract List<String> getAllItems();

	public abstract List<String> getAllByItemName(String paramString);

	public abstract List<Dictionary> getByItemName(String paramString);
}
