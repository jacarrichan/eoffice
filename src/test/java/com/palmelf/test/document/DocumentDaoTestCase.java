package com.palmelf.test.document;

import com.palmelf.core.web.paging.PagingBean;
import com.palmelf.eoffice.dao.document.DocumentDao;
import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.model.document.Document;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.test.BaseTestCase;

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
