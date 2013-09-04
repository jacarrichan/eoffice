package com.palmelf.core.command;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class FieldCommandImpl implements CriteriaCommand {
	private static Log logger = LogFactory.getLog(CriteriaCommand.class);
	private String property;
	private Object value;
	private String operation;
	private QueryFilter filter;

	public FieldCommandImpl(String property, Object value, String operation,
			QueryFilter filter) {
		this.property = property;
		this.value = value;
		this.operation = operation;
		this.filter = filter;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Criteria execute(Criteria criteria) {
		String[] propertys = this.property.split("[.]");

		if ((propertys != null) && (propertys.length > 1)
				&& (!"vo".equals(propertys[0]))) {
			for (int i = 0; i < propertys.length - 1; i++) {
				if (!this.filter.getAliasSet().contains(propertys[i])) {
					criteria.createAlias(propertys[i], propertys[i]);
					this.filter.getAliasSet().add(propertys[i]);
				}
			}

		}

		if ("LT".equals(this.operation))
			criteria.add(Restrictions.lt(this.property, this.value));
		else if ("GT".equals(this.operation))
			criteria.add(Restrictions.gt(this.property, this.value));
		else if ("LE".equals(this.operation))
			criteria.add(Restrictions.le(this.property, this.value));
		else if ("GE".equals(this.operation))
			criteria.add(Restrictions.ge(this.property, this.value));
		else if ("LK".equals(this.operation))
			criteria.add(Restrictions.like(this.property,
					"%" + this.value + "%").ignoreCase());
		else if ("LFK".equals(this.operation))
			criteria.add(Restrictions.like(this.property, this.value + "%")
					.ignoreCase());
		else if ("RHK".equals(this.operation))
			criteria.add(Restrictions.like(this.property, "%" + this.value)
					.ignoreCase());
		else if ("NULL".equals(this.operation))
			criteria.add(Restrictions.isNull(this.property));
		else if ("NOTNULL".equals(this.operation))
			criteria.add(Restrictions.isNotNull(this.property));
		else if ("EMP".equals(this.operation))
			criteria.add(Restrictions.isEmpty(this.property));
		else if ("NOTEMP".equals(this.operation))
			criteria.add(Restrictions.isNotEmpty(this.property));
		else if ("NEQ".equals(this.operation))
			criteria.add(Restrictions.ne(this.property, this.value));
		else {
			criteria.add(Restrictions.eq(this.property, this.value));
		}

		return criteria;
	}

	public String getPartHql() {
		String[] propertys = this.property.split("[.]");
		if ((propertys != null) && (propertys.length > 1)
				&& (!"vo".equals(propertys[0]))) {
			if (!this.filter.getAliasSet().contains(propertys[0])) {
				this.filter.getAliasSet().add(propertys[0]);
			}
		}

		String partHql = "";
		if ("LT".equals(this.operation)) {
			partHql = this.property + " < ? ";
			this.filter.getParamValueList().add(this.value);
		} else if ("GT".equals(this.operation)) {
			partHql = this.property + " > ? ";
			this.filter.getParamValueList().add(this.value);
		} else if ("LE".equals(this.operation)) {
			partHql = this.property + " <= ? ";
			this.filter.getParamValueList().add(this.value);
		} else if ("GE".equals(this.operation)) {
			partHql = this.property + " >= ? ";
			this.filter.getParamValueList().add(this.value);
		} else if ("LK".equals(this.operation)) {
			partHql = this.property + " like ? ";
			this.filter.getParamValueList().add(
					"%" + this.value.toString() + "%");
		} else if ("LFK".equals(this.operation)) {
			partHql = this.property + " like ? ";
			this.filter.getParamValueList().add(this.value.toString() + "%");
		} else if ("RHK".equals(this.operation)) {
			partHql = this.property + " like ? ";
			this.filter.getParamValueList().add("%" + this.value.toString());
		} else if ("NULL".equals(this.operation)) {
			partHql = this.property + " is null ";
		} else if ("NOTNULL".equals(this.operation)) {
			partHql = this.property + " is not null ";
		} else if (!"EMP".equals(this.operation)) {
			if (!"NOTEMP".equals(this.operation)) {
				if ("NEQ".equals(this.operation)) {
					partHql = this.property + " !=? ";
					this.filter.getParamValueList().add(this.value);
				} else {
					partHql = partHql + this.property + " =? ";
					this.filter.getParamValueList().add(this.value);
				}
			}
		}
		return partHql;
	}
}
