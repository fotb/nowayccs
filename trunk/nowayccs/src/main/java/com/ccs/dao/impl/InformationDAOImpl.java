package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

@Repository("informationDAO")
public class InformationDAOImpl extends DefaultDAOSupport implements
		IInformationDAO {

	@Override
	public void saveOrUpate(InformationVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(InformationVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public InformationVO findById(String infoId) {
		return getHibernateTemplate().get(InformationVO.class, infoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationVO> findByHelpTel(final String helpTel, final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final String hql = "from InformationVO t where t.helpTel = ? order by t.createTime";
				Query query = session.createQuery(hql);
				query.setParameter(0, helpTel);
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@Override
	public int getTotalCount(String helpTel) {
		final String hql = "select count(t.infoId) from InformationVO t where t.helpTel = ? order by t.createTime";
		final Long count = (Long) getHibernateTemplate().find(hql, helpTel).listIterator().next();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationVO> findByCreatorAndStatus(final String userId,
			final String status, final String helpType, final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final String hql = "from InformationVO t where t.creator = ? and t.status = ? and t.helpType = ? order by t.createTime desc";
				Query query = session.createQuery(hql);
				query.setParameter(0, userId);
				query.setParameter(1, status);
				query.setParameter(2, helpType);
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@Override
	public int getTotalCount(String userId, String status, String helpType) {
		final String hql = "select count(t.infoId) from InformationVO t where t.creator = ? and t.status = ? and t.helpType = ?";
		final Long count = (Long) getHibernateTemplate().find(hql, new Object[]{userId, status, helpType}).listIterator().next();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationVO> findByDeliverer(final String deliverer, final String helpType,
			final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
				final String hql = "from InformationVO t where t.deliverer = ? and t.helpType = ? and t.status = ? order by t.deliverTime desc";
				Query query = session.createQuery(hql);
				query.setParameter(0, deliverer);
				query.setParameter(1, helpType);
				query.setParameter(2, Constants.SYS_INFOMATION_STATES_CLZ);
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@Override
	public int getTotalCountByDeliverer(String deliverer, String helpType) {
		final String hql = "select count(t.infoId) from InformationVO t where t.deliverer = ? and t.helpType = ? and t.status = ?";
		final Long count = (Long) getHibernateTemplate().find(hql, new Object[]{deliverer, helpType, Constants.SYS_INFOMATION_STATES_CLZ}).listIterator().next();
		return count.intValue();
	}

}

