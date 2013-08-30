package com.cyjt.oa.service.customer.impl;

import com.cyjt.core.service.impl.BaseServiceImpl;
import com.cyjt.oa.dao.customer.ProductDao;
import com.cyjt.oa.model.customer.Product;
import com.cyjt.oa.service.customer.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product> implements
		ProductService {
	private ProductDao dao;

	public ProductServiceImpl(ProductDao dao) {
		super(dao);
		this.dao = dao;
	}
}
