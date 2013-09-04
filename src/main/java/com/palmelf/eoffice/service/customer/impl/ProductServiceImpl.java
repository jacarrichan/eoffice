package com.palmelf.eoffice.service.customer.impl;

import com.palmelf.core.service.impl.BaseServiceImpl;
import com.palmelf.eoffice.dao.customer.ProductDao;
import com.palmelf.eoffice.model.customer.Product;
import com.palmelf.eoffice.service.customer.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product> implements
		ProductService {
	private ProductDao dao;

	public ProductServiceImpl(ProductDao dao) {
		super(dao);
		this.dao = dao;
	}
}
