package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.CustomerDao;
import com.palmelf.eoffice.model.customer.Customer;
import com.palmelf.eoffice.service.customer.CustomerService;

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
