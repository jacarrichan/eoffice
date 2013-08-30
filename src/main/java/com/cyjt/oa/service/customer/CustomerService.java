package com.cyjt.oa.service.customer;

import com.cyjt.core.service.BaseService;
import com.cyjt.oa.model.customer.Customer;

public abstract interface CustomerService extends BaseService<Customer> {
	public abstract boolean checkCustomerNo(String paramString);
}
