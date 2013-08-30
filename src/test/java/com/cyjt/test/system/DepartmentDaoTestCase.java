package com.cyjt.test.system;

import com.cyjt.oa.dao.system.DepartmentDao;
import com.cyjt.oa.model.system.Department;
import com.cyjt.test.BaseTestCase;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DepartmentDaoTestCase extends BaseTestCase {

	@Resource
	private DepartmentDao departmentDao;

	@Test
	@Rollback(false)
	public void batchAdd() {
		for (int i = 1; i <= 100; i++) {
			Department dep = new Department();
			dep.setDepLevel(Integer.valueOf(1));
			dep.setDepName("局" + i);
			dep.setDepDesc("局" + i);
			dep.setParentId(new Long(0L));
			this.departmentDao.save(dep);

			dep.setPath("0." + dep.getDepId() + ".");

			this.departmentDao.save(dep);

			for (int j = 1; j <= 5; j++) {
				Department subDep = new Department();
				subDep.setDepLevel(Integer.valueOf(2));
				subDep.setParentId(dep.getDepId());
				subDep.setDepName(dep.getDepName() + "-" + j);
				subDep.setDepDesc(dep.getDepName() + "-" + j);
				this.departmentDao.save(subDep);
				subDep.setPath(dep.getPath() + subDep.getDepId() + ".");
			}
		}
	}
}
