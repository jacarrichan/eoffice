package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.CustomerDao;
import com.cyjt.oa.model.customer.Customer;
import com.cyjt.oa.service.customer.CustomerService;

public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements
		CustomerService {
	private CustomerDao dao;

	public CustomerServiceImpl(CustomerDao dao) {
		super(dao);
		this.dao = dao;
	}

	public boolean checkCustomerNo(String customerNo) {
		return this.dao.checkCustomerNo(customerNo);
	}
}
