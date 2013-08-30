package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.NewsTypeDao;
import com.cyjt.oa.model.info.NewsType;
import java.util.ArrayList;
import java.util.List;

public class NewsTypeDaoImpl extends BaseDaoImpl<NewsType> implements
		NewsTypeDao {
	public NewsTypeDaoImpl() {
		super(NewsType.class);
	}

	public Short getTop() {
		String hql = "select max(sn) from NewsType";
		List list = findByHql("select max(sn) from NewsType");
		return (Short) list.get(0);
	}

	public List<NewsType> getAllBySn() {
		String hql = "from NewsType nt order by nt.sn asc";
		return findByHql("from NewsType nt order by nt.sn asc");
	}

	public List<NewsType> getAllBySn(PagingBean pb) {
		String hql = "from NewsType nt order by nt.sn asc";
		return findByHql("from NewsType nt order by nt.sn asc", null, pb);
	}

	public NewsType findBySn(Short sn) {
		String hql = "from NewsType nt where nt.sn=?";
		Object[] objs = { sn };
		List list = findByHql("from NewsType nt where nt.sn=?", objs);
		return (NewsType) list.get(0);
	}

	public List<NewsType> findBySearch(NewsType newsType, PagingBean pb) {
		StringBuffer hql = new StringBuffer("from NewsType nt where 1=1 ");
		List params = new ArrayList();
		if (newsType != null) {
			if ((!"".equals(newsType.getTypeName()))
					&& (newsType.getTypeName() != null)) {
				hql.append("and nt.typeName like ?");
				params.add("%" + newsType.getTypeName() + "%");
			}
			if ((newsType.getSn() != null)
					&& (newsType.getSn().shortValue() > 0)) {
				hql.append("and nt.sn = ?");
				params.add(newsType.getSn());
			}
		}
		hql.append("order by nt.sn asc");
		return findByHql(hql.toString(), params.toArray(), pb);
	}
}
