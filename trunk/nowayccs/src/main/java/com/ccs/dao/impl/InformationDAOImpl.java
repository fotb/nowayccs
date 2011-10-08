package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.bean.InfoSearchBean;
import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationVO> findByParams(final InfoSearchBean bean, final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
						final StringBuffer buffer = new StringBuffer(1000);
						List<Object> values = new ArrayList<Object>();
						Type[] types = new Type[16];
						buffer.append("from InformationVO t where ");
						buffer.append("(t.creator = ? or ? is null) ");
						values.add(bean.getCreator());
						values.add(bean.getCreator());
						types[0] = StandardBasicTypes.STRING;
						types[1] = StandardBasicTypes.STRING;
						buffer.append("and (t.helpType = ? or ? is null) ");
						values.add(bean.getHelpType());
						values.add(bean.getHelpType());
						types[2] = StandardBasicTypes.STRING;
						types[3] = StandardBasicTypes.STRING;
						buffer.append("and (t.helpArea = ? or ? is null) ");
						values.add(bean.getHelpArea());
						values.add(bean.getHelpArea());
						types[4] = StandardBasicTypes.STRING;
						types[5] = StandardBasicTypes.STRING;
						buffer.append("and (t.helpGroup = ? or ? is null) ");
						values.add(bean.getHelpGroup());
						values.add(bean.getHelpGroup());
						types[6] = StandardBasicTypes.STRING;
						types[7] = StandardBasicTypes.STRING;
						buffer.append("and (trunc(t.createTime) >= ? or ? is null) ");
						values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
						values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
						types[8] = StandardBasicTypes.DATE;
						types[9] = StandardBasicTypes.DATE;
						buffer.append("and (trunc(t.createTime) <= ? or ? is null) ");
						values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
						values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
						types[10] = StandardBasicTypes.DATE;
						types[11] = StandardBasicTypes.DATE;
						buffer.append("and (t.helpTel like ? or ? is null) ");
						values.add("%" + bean.getHelpTel() + "%");
						values.add(bean.getHelpTel());
						types[12] = StandardBasicTypes.STRING;
						types[13] = StandardBasicTypes.STRING;
						buffer.append("and (t.helpAddr like ? or ? is null) ");
						values.add("%" + bean.getAddress() + "%");
						values.add(bean.getAddress());
						types[14] = StandardBasicTypes.STRING;
						types[15] = StandardBasicTypes.STRING;
						buffer.append("order by t.createTime desc");
						
						Query query = session.createQuery(buffer.toString());
						query.setParameters(values.toArray(), types);
						query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
			}
		});
	}

	@Override
	public int getTotalCountByParams(InfoSearchBean bean) {
		final StringBuffer buffer = new StringBuffer(1000);
		List<Object> values = new ArrayList<Object>();
		buffer.append("select count(t.infoId) from InformationVO t where ");
		buffer.append("(t.creator = ? or ? is null) ");
		values.add(bean.getCreator());
		values.add(bean.getCreator());
		buffer.append("and (t.helpType = ? or ? is null) ");
		values.add(bean.getHelpType());
		values.add(bean.getHelpType());
		buffer.append("and (t.helpArea = ? or ? is null) ");
		values.add(bean.getHelpArea());
		values.add(bean.getHelpArea());
		buffer.append("and (t.helpGroup = ? or ? is null) ");
		values.add(bean.getHelpGroup());
		values.add(bean.getHelpGroup());
		buffer.append("and (trunc(t.createTime) >= ? or ? is null) ");
		values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		buffer.append("and (trunc(t.createTime) <= ? or ? is null) ");
		values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		buffer.append("and (t.helpTel like ? or ? is null) ");
		values.add("%" + bean.getHelpTel() + "%");
		values.add(bean.getHelpTel());
		buffer.append("and (t.helpAddr like ? or ? is null) ");
		values.add("%" + bean.getAddress() + "%");
		values.add(bean.getAddress());
		final Long count = (Long) getHibernateTemplate().find(buffer.toString(), values.toArray()).listIterator().next();
		return count.intValue();
	}
}

