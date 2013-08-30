package com.cyjt.oa.dao.hrm.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.hrm.UserContractDao;
import com.cyjt.oa.model.hrm.UserContract;

public class UserContractDaoImpl extends BaseDaoImpl<UserContract> implements
		UserContractDao {
	public UserContractDaoImpl() {
		super(UserContract.class);
	}

	public boolean checkContractNo(final String contractNo) {
		final String hql = "select count(*) from UserContract uc where uc.contractNo = ?";
		Long count = (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setString(0, contractNo);
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}

	public List<UserContract> findTime(Long contractId) {
		return this
				.findByHql(
						"select uc.expireDate from UserContract uc where uc.contractId = ?",
						new Object[] { contractId });
	}

	public List<UserContract> findByExpireDate() {
		String hql = "from UserContract uc where uc.status=2 order by uc.expireDate desc";
		return this.findByHql(hql);
	}
}
