package com.palmelf.eoffice.dao.customer.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.customer.ProductDao;
import com.palmelf.eoffice.model.customer.Product;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
	public ProductDaoImpl() {
		super(Product.class);
	}
}
