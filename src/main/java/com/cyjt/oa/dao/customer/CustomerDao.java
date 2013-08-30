package com.cyjt.oa.dao.customer;

import com.cyjt.core.dao.BaseDao;
import com.cyjt.oa.model.customer.Customer;

public abstract interface CustomerDao extends BaseDao<Customer> {
	public abstract boolean checkCustomerNo(String paramString);
}
