package com.palmelf.test.admin;

import com.palmelf.eoffice.dao.admin.OfficeGoodsDao;
import com.palmelf.test.BaseTestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;

public class OfficeGoodsDaoTestCase extends BaseTestCase {

	@Resource
	private OfficeGoodsDao officeGoodsDao;

	@Test
	public void test1() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
		System.out.print(sdf.format(date));
	}
}
