package com.palmelf.test.system;

import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.palmelf.core.util.StringUtil;
import com.palmelf.eoffice.dao.system.AppUserDao;
import com.palmelf.eoffice.dao.system.DepartmentDao;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.Department;
import com.palmelf.test.BaseTestCase;

public class AppUserDaoTestCase extends BaseTestCase {

	@Resource
	private AppUserDao appUserDao;

	@Resource
	private DepartmentDao departmentDao;

	/**
	 * 可以用来测试hibernate到MySQL的中文字符问题
	 */
	@Test
	public void update() {
		AppUser user = appUserDao.get((long) 4);
		user.setAddress("家庭住址");
		appUserDao.merge(user);
	}

	public void add() {
		Department department = this.departmentDao.get(new Long(1L));
		DecimalFormat df = new DecimalFormat("000");
		for (int i = 20; i <= 120; i++) {
			AppUser appUser = new AppUser();
			appUser.setTitle(new Short("1"));
			appUser.setDelFlag(new Short("0"));
			appUser.setEmail("user" + i + "@jsoft.com");
			appUser.setUsername("user" + i);
			appUser.setEducation("大学本科");
			appUser.setMobile("135803XX" + df.format(new Double(i)));
			appUser.setPassword("9uCh4qxBlFqap/+KiqoM68EqO8yYGpKa1c+BCgkOEa4=");
			appUser.setDepartment(department);
			appUser.setStatus(new Short("1"));
			appUser.setAccessionTime(new Date());
			appUser.setFullname("张三" + i);

			this.appUserDao.save(appUser);
		}
	}

	public void addDep() {
		Department dep = new Department();
		dep.setDepName("Root Dep");
		dep.setDepLevel(Integer.valueOf(1));

		this.departmentDao.save(dep);
	}

	public void bacthAdd() {
		Department dep = this.departmentDao.get(Long.valueOf(1L));
		for (int i = 101; i < 102; i++) {
			AppUser au = new AppUser();

			au.setTitle((short) 1);
			au.setUsername("user" + i);
			au.setPassword("1");
			au.setFullname("李海" + i);
			au.setAddress("testAddress");
			au.setEducation("test");
			au.setEmail("user" + i + "@cyjt.com");
			au.setAccessionTime(new Date());
			au.setPhoto("photo");
			au.setZip("00003");
			au.setStatus((short) 1);
			au.setFax("020-003034034");
			au.setPosition("UserManager");

			this.appUserDao.save(au);
		}
	}

	@Test
	public void resetPassword() {
		System.out.println(StringUtil.encryptSha256("admin"));
	}
}
