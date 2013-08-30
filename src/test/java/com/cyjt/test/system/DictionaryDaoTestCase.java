package com.cyjt.test.system;

import com.cyjt.oa.dao.system.DictionaryDao;
import com.cyjt.oa.model.system.Dictionary;
import com.cyjt.test.BaseTestCase;
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
