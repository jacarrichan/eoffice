package com.cyjt.oa.dao.customer.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.oa.dao.customer.ProductDao;
import com.cyjt.oa.model.customer.Product;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
	public ProductDaoImpl() {
		super(Product.class);
	}
}
