package com.cyjt.core.command;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public class SortCommandImpl implements CriteriaCommand {
	private String sortName;
	private String ascDesc;
	private QueryFilter filter;

	public Criteria execute(Criteria criteria) {
		String[] propertys = this.sortName.split("[.]");
		if ((propertys != null) && (propertys.length > 1)) {
			for (int i = 0; i < propertys.length - 1; i++) {
				if (!this.filter.getAliasSet().contains(propertys[i])) {
					criteria.createAlias(propertys[i], propertys[i]);
					this.filter.getAliasSet().add(propertys[i]);
				}
			}
		}
		if ("desc".equalsIgnoreCase(this.ascDesc))
			criteria.addOrder(Order.desc(this.sortName));
		else if ("asc".equalsIgnoreCase(this.ascDesc)) {
			criteria.addOrder(Order.asc(this.sortName));
		}
		return criteria;
	}

	public SortCommandImpl(String sortName, String ascDesc, QueryFilter filter) {
		this.sortName = sortName;
		this.ascDesc = ascDesc;
		this.filter = filter;
	}

	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getAscDesc() {
		return this.ascDesc;
	}

	public void setAscDesc(String ascDesc) {
		this.ascDesc = ascDesc;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.sortName)
				.append(this.ascDesc).toHashCode();
	}

	public String getPartHql() {
		return this.sortName + " " + this.ascDesc;
	}
}
