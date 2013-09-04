package com.palmelf.core.model;

import flexjson.JSON;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Log logger = LogFactory.getLog(BaseModel.class);
	private Integer version;

	@JSON(include = false)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
