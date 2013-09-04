package com.palmelf.eoffice.service.customer;

import com.palmelf.core.service.BaseService;
import com.palmelf.eoffice.model.customer.Customer;

public abstract interface CustomerService extends BaseService<Customer> {
	public abstract boolean checkCustomerNo(String paramString);
}
