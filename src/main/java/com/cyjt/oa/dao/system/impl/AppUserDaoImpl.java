package com.cyjt.oa.dao.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cyjt.core.Constants;
import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.system.AppUserDao;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.model.system.AppUser;
import com.cyjt.oa.model.system.Department;
import com.cyjt.oa.model.system.UserAgent;
import com.cyjt.oa.model.system.UserSub;

public class AppUserDaoImpl extends BaseDaoImpl<AppUser> implements AppUserDao,
		UserDetailsService {
	public AppUserDaoImpl() {
		super(AppUser.class);
	}

	public AppUser findByUserName(String username) {
		String hql = "from AppUser au where au.username=?";
		Object[] params = { username };
		List<AppUser> list = this.findByHql(hql, params);
		AppUser user = null;
		if (list.size() != 0) {
			user = list.get(0);
			String hql2 = "select count(*) from AppUser";
			Object obj = this.findUnique(hql2, null);
			if (new Integer(obj.toString()).intValue() > 21) {
				user.setStatus(Short.valueOf((short) 0));
			}
		}

		return user;
	}

	public static void main(String[] args) {
		new AppUserDaoImpl().findByHql("select * from AppUser");
	}

	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException, DataAccessException {
		AppUser user = (AppUser) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from AppUser ap where ap.username=? and ap.delFlag = ?";
						Query query = session.createQuery(hql);
						query.setString(0, username);
						query.setShort(1, Constants.FLAG_UNDELETED.shortValue());
						AppUser user = null;
						try {
							user = (AppUser) query.uniqueResult();

							if (user != null) {
								Hibernate.initialize(user.getRoles());
								Hibernate.initialize(user.getDepartment());

								Set<?> roleSet = user.getRoles();
								Iterator<?> it = roleSet.iterator();

								while (it.hasNext()) {
									AppRole role = (AppRole) it.next();
									if (role.getRoleId().equals(
											AppRole.SUPER_ROLEID)) {
										user.getRights().clear();
										user.getRights().add("__ALL");
										break;
									}
									if (!StringUtils.isNotEmpty(role
											.getRights())) {
										continue;
									}
									String[] items = role.getRights().split(
											"[,]");
									for (String item : items) {
										if (user.getRights().contains(item)) {
											continue;
										}
										user.getRights().add(item);
									}
								}

							}

						} catch (Exception ex) {
							AppUserDaoImpl.this.logger.warn("user:" + username
									+ " can't not loding rights:"
									+ ex.getMessage());
						}
						return user;
					}
				});
		String hql2 = "select count(*) from AppUser";
		Object obj = this.findUnique(hql2, null);
		if (new Integer(obj.toString()).intValue() > 21) {
			user.setStatus(Short.valueOf((short) 0));
		}
		return user;
	}

	public List<AppUser> findByDepartment(String path, PagingBean pb) {
		List list = new ArrayList();
		String hql = new String();
		if ("0.".equals(path)) {
			hql = "from AppUser vo2 where vo2.delFlag = ?";
			list.add(Constants.FLAG_UNDELETED);
		} else {
			hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ?";
			list.add(path + "%");
			list.add(Constants.FLAG_UNDELETED);
		}
		return this.findByHql(hql, list.toArray(), pb);
	}

	public List<AppUser> findByDepartment(Department department) {
		String hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ?";
		Object[] params = { department.getPath() + "%",
				Constants.FLAG_UNDELETED };
		return this.findByHql(hql, params);
	}

	public List<AppUser> findByRole(Long roleId) {
		String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED };
		return this.findByHql(hql, objs);
	}

	public List<AppUser> findByRole(Long roleId, PagingBean pb) {
		String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED };
		return this.findByHql(hql, objs, pb);
	}

	public List<AppUser> findByDepartment(String path) {
		String hql = "select vo2 from Department vo1,AppUser vo2 where vo1.depId=vo2.depId and vo1.path like ? and vo2.delFlag =?";
		Object[] params = { path + "%", Constants.FLAG_UNDELETED };
		return this.findByHql(hql, params);
	}

	public List<AppUser> findByRoleId(Long roleId) {
		String hql = "select vo from AppUser vo join vo.roles as roles where roles.roleId=? and vo.delFlag =?";
		return this.findByHql(hql, new Object[] { roleId,
				Constants.FLAG_UNDELETED });
	}

	public List<AppUser> findByUserIds(Long[] userIds) {
		String hql = "select vo from AppUser vo where vo.delFlag=? ";

		if ((userIds == null) || (userIds.length == 0)) {
			return null;
		}
		hql = hql + " where vo.userId in (";
		int i = 0;
		for (Long userId : userIds) {
			if (i++ > 0) {
				hql = hql + ",";
			}
			hql = hql + "?";
		}
		hql = hql + " )";

		return this.findByHql(hql, new Object[] { Constants.FLAG_UNDELETED,
				userIds });
	}

	public List<AppUser> findSubAppUser(String path, Set<Long> userIds,
			PagingBean pb) {
		String st = "";
		if (userIds.size() > 0) {
			Iterator<Long> it = userIds.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append((it.next()).toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			st = sb.toString();
		}

		List list = new ArrayList();
		StringBuffer hql = new StringBuffer();
		if (path != null) {
			hql.append("select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department ");
			hql.append(" and vo1.path like ?");
			list.add(path + "%");
		} else {
			hql.append("from AppUser vo2 where 1=1 ");
		}
		if (st != "") {
			hql.append(" and vo2.userId not in (" + st + ")");
		}
		hql.append(" and vo2.delFlag = ?");
		list.add(Constants.FLAG_UNDELETED);
		return this.findByHql(hql.toString(), list.toArray(), pb);
	}

	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds,
			PagingBean pb) {
		String st = "";
		if (userIds.size() > 0) {
			Iterator<Long> it = userIds.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append((it.next()).toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			st = sb.toString();
		}
		StringBuffer hql = new StringBuffer(
				"select vo from AppUser vo join vo.roles roles where roles.roleId=?");
		List<Number> list = new ArrayList<Number>();
		list.add(roleId);
		if (st != "") {
			hql.append(" and vo.userId not in (" + st + ")");
		}
		hql.append(" and vo.delFlag =?");
		list.add(Constants.FLAG_UNDELETED);
		return this.findByHql(hql.toString(), list.toArray(), pb);
	}

	public List<AppUser> findByDepId(Long depId) {
		String hql = "from AppUser vo where vo.delFlag=0 and vo.department.depId=?";
		Object[] objs = { depId };
		return this.findByHql(hql, objs);
	}

	public List<AppUser> getUnAgents(final Long userId, String fullname,
			PagingBean pb) {
		List<UserAgent> userAgents = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("from UserAgent ua where ua.userId=?");
						query.setLong(0, userId.longValue());
						return query.list();
					}
				});
		String hql2 = "from AppUser au where au.status=? ";
		ArrayList params = new ArrayList();
		params.add(Constants.ENABLED);

		if (StringUtils.isNotEmpty(fullname)) {
			hql2 = hql2 + " and au.fullname like ?";
			params.add("%" + fullname + "%");
		}

		StringBuffer sb = new StringBuffer(userId.toString());
		for (UserAgent userAgent : userAgents) {
			sb.append(",").append(userAgent.getGrantUId());
		}
		hql2 = hql2 + " and au.userId not in(" + sb.toString() + ")";

		return this.findByHql(hql2, params.toArray(), pb);
	}

	public List<AppUser> getUnSubUser(final Long userId, String fullname,
			PagingBean pb) {
		final String hql = "from UserSub ua where ua.userId=?";
		List<UserSub> userSubs = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setLong(0, userId.longValue());
						return query.list();
					}
				});
		String hql2 = "from AppUser au where au.status=? ";
		ArrayList params = new ArrayList();
		params.add(Constants.ENABLED);

		if (StringUtils.isNotEmpty(fullname)) {
			hql2 = hql2 + " and au.fullname like ?";
			params.add("%" + fullname + "%");
		}

		StringBuffer sb = new StringBuffer(userId.toString());
		for (UserSub userSub : userSubs) {
			sb.append(",").append(userSub.getUserId());
		}
		hql2 = hql2 + " and au.userId not in(" + sb.toString() + ")";

		return this.findByHql(hql2, params.toArray(), pb);
	}
}
