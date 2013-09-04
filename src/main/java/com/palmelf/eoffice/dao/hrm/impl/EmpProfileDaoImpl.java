package com.palmelf.eoffice.dao.hrm.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.hrm.EmpProfileDao;
import com.palmelf.eoffice.model.hrm.EmpProfile;

public class EmpProfileDaoImpl extends BaseDaoImpl<EmpProfile> implements
		EmpProfileDao {
	public EmpProfileDaoImpl() {
		super(EmpProfile.class);
	}

	public boolean checkProfileNo(final String profileNo) {
		Long count = (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select count(*) from EmpProfile ep where ep.profileNo = ?");
						query.setString(0, profileNo);
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}
}
