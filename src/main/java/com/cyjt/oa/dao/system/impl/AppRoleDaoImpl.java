package com.cyjt.oa.dao.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.system.AppRoleDao;
import com.cyjt.oa.model.system.AppFunction;
import com.cyjt.oa.model.system.AppRole;
import com.cyjt.oa.model.system.FunUrl;

public class AppRoleDaoImpl extends BaseDaoImpl<AppRole> implements AppRoleDao {
	public AppRoleDaoImpl() {
		super(AppRole.class);
	}

	public AppRole getByRoleName(String roleName) {
		String hql = "from AppRole ar where ar.roleName=?";
		return (AppRole) this.findUnique(hql, new Object[] { roleName });
	}

	public HashMap<String, Set<String>> getSecurityDataSource() {
		final HashMap<String, Set<String>> source = new HashMap<String, Set<String>>();

		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from AppRole";
				Query query = session.createQuery(hql);
				List<AppRole> roleList = query.list();

				for (AppRole role : roleList) {
					TreeSet<String> urlSet = new TreeSet<String>();

					Iterator<?> functions = role.getFunctions().iterator();

					while (functions.hasNext()) {
						AppFunction fun = (AppFunction) functions.next();

						Iterator<?> urlIt = fun.getFunUrls().iterator();
						while (urlIt.hasNext()) {
							String url = ((FunUrl) urlIt.next()).getUrlPath();

							urlSet.add(url);
						}
					}

					source.put(role.getName(), urlSet);
				}
				return null;
			}
		});
		return source;
	}
}
