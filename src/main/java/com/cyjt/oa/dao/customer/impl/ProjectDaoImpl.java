package com.cyjt.oa.dao.customer.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.ProjectDao;
import com.cyjt.oa.model.customer.Project;

public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {
	public ProjectDaoImpl() {
		super(Project.class);
	}

	public boolean checkProjectNo(final String projectNo) {
		Long count = (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select count(*) from Project p where p.projectNo = ?");
						query.setString(0, projectNo);
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}
}
