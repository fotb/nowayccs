package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.bean.AffairInfoSearchBean;
import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.LifeInfoSearchBean;
import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
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
				final String hql = "from InformationVO t where t.helpTel = ? order by t.createTime desc";
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
		final String hql = "select count(t.infoId) from InformationVO t where t.helpTel = ?";
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
						Type[] types = new Type[18];
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
						buffer.append("and (t.helpContent like ? or ? is null) ");
						values.add("%" + bean.getHelpContent() + "%");
						values.add(bean.getHelpContent());
						types[16] = StandardBasicTypes.STRING;
						types[17] = StandardBasicTypes.STRING;					
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
		buffer.append("and (t.helpContent like ? or ? is null) ");
		values.add("%" + bean.getHelpContent() + "%");
		values.add(bean.getHelpContent());
		final Long count = (Long) getHibernateTemplate().find(buffer.toString(), values.toArray()).listIterator().next();
		return count.intValue();
	}

	@Override
	public int getInfoCount(String helpType) {
		final String hql = "select count(t.infoId) from InformationVO t where t.helpType = ?";
		final Long count = (Long) getHibernateTemplate().find(hql, helpType).listIterator().next();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationVO> findLifeInfoByParams(final LifeInfoSearchBean bean,
			final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
						final StringBuffer buffer = new StringBuffer(1000);
						List<Object> values = new ArrayList<Object>();
						List<Type> typeList = new ArrayList<Type>();
						buffer.append("select t from InformationVO t, LifeInformationVO l");
						
						if(!StringUtil.isNull(bean.getReceiverType())) { 
							if(Constants.LIFEINFOMATION_RECEIVETYPE_ZYZ.equals(bean.getReceiverType())) {
								buffer.append(", VolunteerVO v where ");
								buffer.append("l.receiverId = v.volunteerId ");
								buffer.append("and (v.volunteerName like ? or ? is null)");
							} else {
								buffer.append(", EntpriseVO e where ");
								buffer.append("l.receiverId = e.entpriseId ");
								buffer.append("and (e.entpriseName like ? or ? is null)");
							}
							values.add("%" + bean.getKeyWords() + "%");
							values.add(bean.getKeyWords());
							typeList.add(StandardBasicTypes.STRING);
							typeList.add(StandardBasicTypes.STRING);
							buffer.append("and ");
						} else {
							buffer.append(" where ");
						}
						buffer.append(" t.infoId = l.infoId ");
						buffer.append(" and t.helpType = ? ");
						values.add(Constants.INFOMATION_HELPTYPE_LIFE);
						typeList.add(StandardBasicTypes.STRING);
						
						buffer.append("and (t.helpArea = ? or ? is null)");
						values.add(bean.getHelpArea());
						values.add(bean.getHelpArea());
						typeList.add(StandardBasicTypes.STRING);
						typeList.add(StandardBasicTypes.STRING);
						
						buffer.append("and (t.helpArea = ? or ? is null)");
						values.add(bean.getHelpArea());
						values.add(bean.getHelpArea());
						typeList.add(StandardBasicTypes.STRING);
						typeList.add(StandardBasicTypes.STRING);
						
						buffer.append("and (trunc(t.createTime) >= ? or ? is null) ");
						values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
						values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
						typeList.add(StandardBasicTypes.DATE);
						typeList.add(StandardBasicTypes.DATE);
						buffer.append("and (trunc(t.createTime) <= ? or ? is null) ");
						values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
						values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
						typeList.add(StandardBasicTypes.DATE);
						typeList.add(StandardBasicTypes.DATE);
						
						
						buffer.append("order by t.createTime desc");
						
						Type[] types = new Type[typeList.size()];
						int i = 0;
						for (Iterator<Type> iter = typeList.iterator(); iter.hasNext();) {
							Type type = iter.next();
							types[i] = type;
							i++;
						}
						Query query = session.createQuery(buffer.toString());
						query.setParameters(values.toArray(), types);
						query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
			}
		});
	}

	@Override
	public int getLifeCountByParams(LifeInfoSearchBean bean) {
		final StringBuffer buffer = new StringBuffer(1000);
		List<Object> values = new ArrayList<Object>();
		buffer.append("select count(t.infoId) from InformationVO t, LifeInformationVO l");
		if(!StringUtil.isNull(bean.getReceiverType())) { 
			if(Constants.LIFEINFOMATION_RECEIVETYPE_ZYZ.equals(bean.getReceiverType())) {
				buffer.append(", VolunteerVO v where ");
				buffer.append("l.receiverId = v.volunteerId ");
				buffer.append("and (v.volunteerName like ? or ? is null)");
			} else {
				buffer.append(", EntpriseVO e where ");
				buffer.append("l.receiverId = e.entpriseId ");
				buffer.append("and (e.entpriseName like ? or ? is null)");
			}
			
			values.add("%" + bean.getKeyWords() + "%");
			values.add(bean.getKeyWords());
			buffer.append("and ");
		} else {
			buffer.append(" where ");
		}
		buffer.append("t.infoId = l.infoId ");
		buffer.append("and t.helpType = ? ");
		values.add(Constants.INFOMATION_HELPTYPE_LIFE);
		
		buffer.append("and (t.helpArea = ? or ? is null)");
		values.add(bean.getHelpArea());
		values.add(bean.getHelpArea());
		
		buffer.append("and (t.helpArea = ? or ? is null)");
		values.add(bean.getHelpArea());
		values.add(bean.getHelpArea());
		
		buffer.append("and (trunc(t.createTime) >= ? or ? is null) ");
		values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		buffer.append("and (trunc(t.createTime) <= ? or ? is null) ");
		values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		
		buffer.append("and (l.helpApprove = ? or ? is null) ");
		values.add(bean.getHelpApprove());
		values.add(bean.getHelpApprove());
		
		buffer.append("and (t.status = ? or ? is null) ");
		values.add(bean.getStatus());
		values.add(bean.getStatus());
		
		final Long count = (Long) getHibernateTemplate().find(buffer.toString(), values.toArray()).listIterator().next();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InformationVO> findAffairInfoByParams(
			final AffairInfoSearchBean bean, final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
						final StringBuffer buffer = new StringBuffer(1000);
						List<Object> values = new ArrayList<Object>();
						Type[] types = new Type[5];
						buffer.append("from InformationVO t where ");
						buffer.append("(trunc(t.createTime) >= ? or ? is null) ");
						values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
						values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
						types[0] = StandardBasicTypes.DATE;
						types[1] = StandardBasicTypes.DATE;
						buffer.append("and (trunc(t.createTime) <= ? or ? is null) ");
						values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
						values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
						types[2] = StandardBasicTypes.DATE;
						types[3] = StandardBasicTypes.DATE;
						buffer.append("and t.helpType = ?" );
						values.add(Constants.INFOMATION_HELPTYPE_AFFAIR);
						types[4] = StandardBasicTypes.STRING;
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
	public int getAffairCountByParams(AffairInfoSearchBean bean) {
		final StringBuffer buffer = new StringBuffer(1000);
		List<Object> values = new ArrayList<Object>();
		buffer.append("select count(t.infoId) from InformationVO t, AffairInformationVO a where ");
		buffer.append("(trunc(t.createTime) >= ? or ? is null) ");
		values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		values.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		buffer.append("and (trunc(t.createTime) <= ? or ? is null) ");
		values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		values.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		buffer.append("and t.helpType = ? " );
		values.add(Constants.INFOMATION_HELPTYPE_AFFAIR);
		buffer.append("and t.infoId = a.infoId ");
		buffer.append("and (a.helpApprove = ? or ? is null) ");
		values.add(bean.getHelpApprove());
		values.add(bean.getHelpApprove());
		
		buffer.append("and (t.status = ? or ? is null) ");
		values.add(bean.getStatus());
		values.add(bean.getStatus());
		
		final Long count = (Long) getHibernateTemplate().find(buffer.toString(), values.toArray()).listIterator().next();
		return count.intValue();
	}
}

