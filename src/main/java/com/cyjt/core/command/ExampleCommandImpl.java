package com.cyjt.core.command;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

public class ExampleCommandImpl implements CriteriaCommand {
	private Object pojoExample = null;

	public void setPojoExample(Object pojoEx) {
		this.pojoExample = pojoEx;
	}

	public ExampleCommandImpl(Object pojoExample) {
		this.pojoExample = pojoExample;
	}

	public Criteria execute(Criteria criteria) {
		if (this.pojoExample != null) {
			Example exampleRestriction = Example.create(this.pojoExample);
			criteria.add(exampleRestriction);
		}
		return criteria;
	}
}
