package com.palmelf.test.system;

import com.palmelf.eoffice.dao.system.DictionaryDao;
import com.palmelf.eoffice.model.system.Dictionary;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DictionaryDaoTestCase extends BaseTestCase {

	@Resource
	private DictionaryDao dictionaryDao;

	@Test
	@Rollback(false)
	public void add() {
		Dictionary dictionary = new Dictionary();

		this.dictionaryDao.save(dictionary);
	}
}
