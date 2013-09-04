package com.palmelf.eoffice.dao.personal.impl;

import com.palmelf.core.dao.impl.BaseDaoImpl;
import com.palmelf.eoffice.dao.personal.DutySystemDao;
import com.palmelf.eoffice.model.personal.DutySystem;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class DutySystemDaoImpl extends BaseDaoImpl<DutySystem> implements
		DutySystemDao {
	public DutySystemDaoImpl() {
		super(DutySystem.class);
	}

	public void updateForNotDefult() {
		String hql = "update DutySystem ds set ds.isDefault=? where ds.isDefault=?";
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session
						.createQuery("update DutySystem ds set ds.isDefault=? where ds.isDefault=?");
				query.setShort(0, DutySystem.NOT_DEFAULT.shortValue());
				query.setShort(1, DutySystem.DEFAULT.shortValue());
				query.executeUpdate();
				return null;
			}
		});
	}

	public List<DutySystem> getDefaultDutySystem() {
		String hql = "from DutySystem ds where ds.isDefault=? ";
		return findByHql(hql, new Object[] { DutySystem.DEFAULT });
	}
}
