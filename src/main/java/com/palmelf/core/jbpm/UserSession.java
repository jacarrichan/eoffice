package com.palmelf.core.jbpm;

import com.palmelf.core.util.AppUtil;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.system.AppRoleService;
import com.palmelf.eoffice.service.system.AppUserService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;

public class UserSession implements IdentitySession {
	private AppUserService appUserService = (AppUserService) AppUtil
			.getBean("appUserService");

	private AppRoleService appRoleService = (AppRoleService) AppUtil
			.getBean("appRoleService");

	public String createGroup(String arg0, String arg1, String arg2) {
		return null;
	}

	public void createMembership(String arg0, String arg1, String arg2) {
	}

	public String createUser(String arg0, String arg1, String arg2, String arg3) {
		return null;
	}

	public void deleteGroup(String arg0) {
	}

	public void deleteMembership(String arg0, String arg1, String arg2) {
	}

	public void deleteUser(String arg0) {
	}

	public Group findGroupById(String groupId) {
		return this.appRoleService.get(new Long(groupId));
	}

	public List<Group> findGroupsByUser(String userId) {
		AppUser user = this.appUserService.get(new Long(userId));
		List list = new ArrayList();
		Iterator it = user.getRoles().iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	public List<Group> findGroupsByUserAndGroupType(String userId,
			String groupType) {
		return findGroupsByUser(userId);
	}

	public User findUserById(String userId) {
		return this.appUserService.get(new Long(userId));
	}

	public List<User> findUsers() {
		List<AppUser> userList = this.appUserService.getAll();
		List list = new ArrayList();
		for (User user : userList) {
			list.add(user);
		}
		return list;
	}

	public List<User> findUsersByGroup(String groupId) {
		List<User> userList = this.appUserService
				.findByRoleId(new Long(groupId));
		List list = new ArrayList();
		for (User user : userList) {
			list.add(user);
		}
		return list;
	}

	public List<User> findUsersById(String[] userIds) {
		return null;
	}
}
