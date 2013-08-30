package com.cyjt.test.hrm;

import com.cyjt.oa.dao.hrm.StandSalaryDao;
import com.cyjt.test.BaseTestCase;
import java.math.BigDecimal;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class StandSalaryDaoTestCase extends BaseTestCase {

	@Resource
	private StandSalaryDao standSalaryDao;

	@Test
	@Rollback(false)
	public void add() {
		BigDecimal abc = new BigDecimal("0");
		BigDecimal abc1 = new BigDecimal("1");
		BigDecimal abc2 = new BigDecimal("2");

		System.out.println(abc.add(abc1).add(abc2));
	}
}
