package com.palmelf.eoffice.service.system;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.system.Dictionary;

import java.util.List;

public abstract interface DictionaryService extends BaseService<Dictionary> {
	public abstract List<String> getAllItems();

	public abstract List<String> getAllByItemName(String paramString);

	public abstract List<Dictionary> getByItemName(String paramString);
}
