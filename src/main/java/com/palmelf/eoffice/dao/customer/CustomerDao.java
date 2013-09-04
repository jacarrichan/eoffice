package com.palmelf.eoffice.dao.customer;

import com.palmelf.core.dao.BaseDao;
import com.palmelf.eoffice.model.customer.Customer;

public abstract interface CustomerDao extends BaseDao<Customer> {
	public abstract boolean checkCustomerNo(String paramString);
}
