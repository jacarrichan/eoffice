package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.Constants;
import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.NewsDao;
import com.cyjt.oa.model.info.News;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {
	public NewsDaoImpl() {
		super(News.class);
	}

	public List<News> findByTypeId(Long typeId, PagingBean pb) {
		String hql = "from News n where n.newsType.typeId=?";
		Object[] params = { typeId };
		return findByHql("from News n where n.newsType.typeId=?", params, pb);
	}

	public List<News> findBySearch(String searchContent, PagingBean pb) {
		ArrayList params = new ArrayList();
		StringBuffer hql = new StringBuffer("from News n where n.status = ?");
		params.add(Constants.FLAG_ACTIVATION);
		if (StringUtils.isNotEmpty(searchContent)) {
			hql.append(" and (n.subject like ? or n.content like ?)");
			params.add("%" + searchContent + "%");
			params.add("%" + searchContent + "%");
		}
		hql.append(" order by n.updateTime desc");
		return findByHql(hql.toString(), params.toArray(), pb);
	}

	public List<News> findImageNews(PagingBean pb) {
		String hql = "from News vo where vo.isDeskImage=1 order by vo.updateTime desc";
		return findByHql(hql, new Object[0], pb);
	}
}
