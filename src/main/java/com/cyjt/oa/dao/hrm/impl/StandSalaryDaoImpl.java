package com.cyjt.oa.dao.hrm.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cyjt.core.Constants;
import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.StandSalaryDao;
import com.cyjt.oa.model.hrm.StandSalary;

public class StandSalaryDaoImpl extends BaseDaoImpl<StandSalary> implements
		StandSalaryDao {
	public StandSalaryDaoImpl() {
		super(StandSalary.class);
	}

	public boolean checkStandNo(final String standardNo) {
		final String hql = "select count(*) from StandSalary ss where ss.standardNo = ?";
		final Long count = (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(final Session session)
							throws HibernateException, SQLException {
						final Query query = session.createQuery(hql);
						query.setString(0, standardNo);
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}

	public List<StandSalary> findByPassCheck() {
		final String hql = "from StandSalary vo where vo.status=?";
		final Object[] objs = { Constants.FLAG_PASS };
		return this.findByHql(hql, objs);
	}
}
