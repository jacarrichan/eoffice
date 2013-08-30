package com.cyjt.oa.dao.info.impl;

import com.cyjt.core.dao.impl.BaseDaoImpl;
import com.cyjt.core.web.paging.PagingBean;
import com.cyjt.oa.dao.info.ShortMessageDao;
import com.cyjt.oa.model.info.ShortMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class ShortMessageDaoImpl extends BaseDaoImpl<ShortMessage> implements
		ShortMessageDao {
	public ShortMessageDaoImpl() {
		super(ShortMessage.class);
	}

	public List<ShortMessage> findAll(Long userId, PagingBean pb) {
		String hql = "from ShortMessage vo where vo.senderId=?";
		Object[] objs = { userId };
		return findByHql(hql, objs, pb);
	}

	public List<ShortMessage> findByUser(Long userId) {
		String hql = "from ShortMessage vo where vo.senderId=?";
		Object[] objs = { userId };
		return findByHql(hql, objs);
	}

	public List searchShortMessage(Long userId, ShortMessage shortMessage,
			Date from, Date to, PagingBean pb) {
		ArrayList paramList = new ArrayList();
		StringBuffer hql = new StringBuffer(
				"select vo1,vo2 from InMessage vo1,ShortMessage vo2 where vo1.shortMessage=vo2 and vo1.delFlag=0 and vo1.userId=?");
		paramList.add(userId);
		if (shortMessage != null) {
			if (shortMessage.getMsgType() != null) {
				hql.append(" and vo2.msgType=?");
				paramList.add(shortMessage.getMsgType());
			}
			if (StringUtils.isNotEmpty(shortMessage.getSender())) {
				hql.append(" and vo2.sender=?");
				paramList.add(shortMessage.getSender());
			}
		}
		if (to != null) {
			hql.append("and vo2.sendTime <= ?");
			paramList.add(to);
		}
		if (from != null) {
			hql.append("and vo2.sendTime >= ?");
			paramList.add(from);
		}
		hql.append(" order by vo2.sendTime desc");
		return findByHql(hql.toString(), paramList.toArray(), pb);
	}
}
