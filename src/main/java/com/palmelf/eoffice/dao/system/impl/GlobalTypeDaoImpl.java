package com.palmelf.eoffice.dao.system.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.system.GlobalTypeDao;
import com.palmelf.eoffice.model.system.GlobalType;

import java.util.ArrayList;
import java.util.List;

public class GlobalTypeDaoImpl extends BaseDaoImpl<GlobalType> implements
		GlobalTypeDao {
	public GlobalTypeDaoImpl() {
		super(GlobalType.class);
	}

	public List<GlobalType> getByParentIdCatKey(Long parentId, String catKey) {
		String hql = " from GlobalType gt where gt.parentId = ? and gt.catKey = ? order by gt.sn asc";
		return findByHql(hql, new Object[] { parentId, catKey });
	}

	public Integer getCountsByParentId(Long parentId) {
		ArrayList param = new ArrayList();
		String hql = " select count(proTypeId) from GlobalType gt ";
		if ((parentId != null) && (parentId.longValue() != 0L)) {
			hql = hql + " where gt.parentId=?";
			param.add(parentId);
		} else {
			hql = hql + " where gt.parentId is null";
		}

		Object obj = findUnique(hql, param.toArray());
		return new Integer(obj.toString());
	}

	public List<GlobalType> getByParentId(Long parentId) {
		ArrayList param = new ArrayList();
		String hql = " from GlobalType gt ";
		if ((parentId != null) && (parentId.longValue() != 0L)) {
			hql = hql + " where gt.parentId=?";
			param.add(parentId);
		} else {
			hql = hql + " where gt.parentId is null";
		}

		return findByHql(hql, param.toArray());
	}

	public List<GlobalType> getByPath(String path) {
		String hql = " from GlobalType gt where gt.path like ?";
		return findByHql(hql, new Object[] { path + "%" });
	}

	public GlobalType findByTypeName(String typeName) {
		String hql = " from GlobalType gt where gt.typeName = ?";
		List list = findByHql(hql, new Object[] { typeName });
		if (list.size() > 0) {
			return (GlobalType) list.get(0);
		}
		return null;
	}
}
