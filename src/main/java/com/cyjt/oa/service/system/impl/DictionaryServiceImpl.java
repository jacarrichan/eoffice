package com.cyjt.oa.service.system.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.system.DictionaryDao;
import com.cyjt.oa.model.system.Dictionary;
import com.cyjt.oa.service.system.DictionaryService;
import java.util.List;

public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary>
		implements DictionaryService {
	private DictionaryDao dao;

	public DictionaryServiceImpl(DictionaryDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<String> getAllItems() {
		return this.dao.getAllItems();
	}

	public List<String> getAllByItemName(String itemName) {
		return this.dao.getAllByItemName(itemName);
	}

	public List<Dictionary> getByItemName(String itemName) {
		return this.dao.getByItemName(itemName);
	}
}
