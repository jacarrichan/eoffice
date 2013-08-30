package com.cyjt.oa.dao.customer.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.CustomerDao;
import com.cyjt.oa.model.customer.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements
		CustomerDao {
	public CustomerDaoImpl() {
		super(Customer.class);
	}

	public boolean checkCustomerNo(final String customerNo) {
		Long count = (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select count(*) from Customer c where c.customerNo = ?");
						query.setString(0, customerNo);
						return query.uniqueResult();
					}
				});
		return count.longValue() == 0L;
	}
}
