package com.cyjt.oa.dao.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.admin.ConfPrivilegeDao;
import com.cyjt.oa.model.admin.ConfPrivilege;

public class ConfPrivilegeDaoImpl extends BaseDaoImpl<ConfPrivilege> implements
		ConfPrivilegeDao {
	public ConfPrivilegeDaoImpl() {
		super(ConfPrivilege.class);
	}

	public Short getPrivilege(Long userId, Long confId, Short s) {
		Short st = Short.valueOf((short) 0);
		String hql = "select p from ConfPrivilege p where p.userId =" + userId
				+ " and p.confId = " + confId + " and p.rights=" + s;
		List list = this.findByHql(hql);
		if ((list != null) && (list.size() > 0)) {
			ConfPrivilege cp = (ConfPrivilege) list.get(0);
			st = cp.getRights();
		}
		return st;
	}

	public void delete(final Long confId) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session
						.createQuery("delete ConfPrivilege c where c.confId =?");
				query.setLong(0, confId);
				query.executeUpdate();
				return null;
			}
		});
	}
}
