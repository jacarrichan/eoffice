package com.palmelf.test.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.palmelf.eoffice.model.system.AppFunction;
import com.palmelf.eoffice.model.system.AppRole;
import com.palmelf.eoffice.service.system.AppFunctionService;
import com.palmelf.eoffice.service.system.AppRoleService;
import com.palmelf.test.BaseTestCase;

import javax.annotation.Resource;
import org.junit.Test;

public class AppRoleTestCase extends BaseTestCase {

	@Resource
	private AppRoleService appRoleService;

	@Resource
	private AppFunctionService appFunctionService;

	public void testMerge() {
		AppRole role = new AppRole();
		role.setRoleId(Long.valueOf(1L));
		role.setStatus((short) 0);
		this.appRoleService.merge(role);
	}
	//@Test
	public void add(){
		AppRole role = new AppRole();
		role.setRoleName("哈哈");
		this.appRoleService.save(role);
	}
	
	
	public void updateFunctions() {
		AppRole role = this.appRoleService.get(Long.valueOf(3L));
		for (int id = 1; id <= 2; id++) {
			AppFunction appFunction = this.appFunctionService.get(new Long(id));
			role.getFunctions().add(appFunction);
		}
		this.appRoleService.save(role);
	}
	@Test
	public void testDao(){
		HashMap<String, Set<String>> sh = this.appRoleService.getSecurityDataSource();
		
		System.out.println(sh);
	}
}
