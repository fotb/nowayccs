package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IReferDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.ReferVO;

@Repository("referDAO")
public class ReferDAOImpl extends DefaultDAOSupport implements IReferDAO {

	@Override
	public void saveoOrUpdate(ReferVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(ReferVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public ReferVO findById(String referId) {
		return getHibernateTemplate().get(ReferVO.class, referId);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<ReferVO> findByParams(final String title, final String referType,
			final PageInfo pageInfo) {
		return (List<ReferVO>) getHibernateTemplate().execute(new HibernateCallback<List<ReferVO>>() {
			@Override
			public List<ReferVO> doInHibernate(Session session)
					throws HibernateException {
				final String hql = "from ReferVO t where (t.title like ? or ? is null) and (t.referType = ? or ? is null) order by title";
				Query query = session.createQuery(hql);
				query.setParameter(0, "%" + title + "%");
				query.setParameter(1, title);
				query.setParameter(2, referType);
				query.setParameter(3, referType);
						query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT()); 
	           return query.list(); 
			}
		});
	}

	@Override
	public int getCountByParams(String title, String referType) {
		final String hql = "select count(t.referId) from ReferVO t where (t.title like ? or ? is null) and (t.referType = ? or ? is null)";
		Long count = (Long) getHibernateTemplate().find(hql, new Object[]{"%" + title + "%", title, referType, referType}).listIterator().next();
		return count.intValue();
	}

}
