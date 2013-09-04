package com.palmelf.eoffice.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.DictionaryDao;
import com.palmelf.eoffice.model.system.Dictionary;

public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements
		DictionaryDao {
	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	public List<String> getAllItems() {
		final String hql = "select itemName from Dictionary group by itemName";
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return query.list();
					}
				});
	}

	public List<String> getAllByItemName(final String itemName) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select itemValue from Dictionary where itemName=?");
						query.setString(0, itemName);
						return query.list();
					}
				});
	}

	public List<Dictionary> getByItemName(final String itemName) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery(" from Dictionary where itemName=?");
						query.setString(0, itemName);
						return query.list();
					}
				});
	}
}
