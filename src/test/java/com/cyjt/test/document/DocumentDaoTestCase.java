package com.cyjt.test.document;

import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.document.DocumentDao;
import com.cyjt.oa.dao.system.AppUserDao;
import com.cyjt.oa.model.document.Document;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.test.BaseTestCase;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;

public class DocumentDaoTestCase extends BaseTestCase {

	@Resource
	private DocumentDao documentDao;

	@Resource
	private AppUserDao appUserDao;

	@Test
	public void tesss() {
		AppUser user = this.appUserDao.get(Long.valueOf(2L));

		PagingBean pb = new PagingBean(0, 6);
		Document document = this.documentDao.get(Long.valueOf(6L));
		List docs = this.documentDao.findByPersonal(Long.valueOf(2L), null,
				null, null, null, pb);
		System.out.println("size:" + docs.size());
	}
}
