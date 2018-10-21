package com.ccs.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ILonelyHelpDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.vo.LonelyHelpVO;

@Repository("lonelyHelpDAO")
public class LonelyHelpDAOImpl extends DefaultDAOSupport implements
		ILonelyHelpDAO {

	@Override
	public void saveOrUpdate(LonelyHelpVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(LonelyHelpVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public LonelyHelpVO findById(String helpId) {
		return getHibernateTemplate().get(LonelyHelpVO.class, helpId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LonelyHelpVO> query(final List<String> lonelyManIds,
			final List<String> memberIds, final Date startDt, final Date endDt, final PageInfo pageInfo) {
		return (List<LonelyHelpVO>) getHibernateTemplate().execute(new HibernateCallback<List<LonelyHelpVO>>() {
			@Override
			public List<LonelyHelpVO> doInHibernate(Session session)
					throws HibernateException {
				String hql = "from LonelyHelpVO vo where 1 = 1 ";
				if(!lonelyManIds.isEmpty()) {
					hql += "and (vo.lonelyManId in (:manId1) or :manId2 is null) ";
				}
				if(!memberIds.isEmpty()) {
					hql += "and (vo.deliverer in (:memberId1) or :memberId2 is null) ";
				}
//				hql +="and (vo.createTime >= :startDt1 or :startDt2 is null) ";
//				hql +="and (vo.createTime <= :endDt1 or :endDt2 is null) ";
				hql +="and (vo.createTime between :startDt and :endDt) ";
				hql += " order by vo.createTime Desc";
				
				Query query = session.createQuery(hql);
				if(!lonelyManIds.isEmpty()) {
					query.setParameterList("manId1", lonelyManIds);
					query.setParameter("manId2", lonelyManIds.isEmpty() ? null : lonelyManIds.toString());
				}
				if(!memberIds.isEmpty()) {
					query.setParameterList("memberId1", memberIds);
					query.setParameter("memberId2", memberIds.isEmpty() ? null : memberIds.toString());
				}
				if(null == startDt) {
					query.setParameter("startDt", DateUtil.parse("1900-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				} else {
					query.setParameter("startDt", startDt);
				}
				if(null == endDt) {
					query.setParameter("endDt", new Date());
				} else {
					query.setParameter("endDt", endDt);
				}
//				query.setParameter("startDt1", startDt);
//				query.setParameter("startDt2", startDt);
//				query.setParameter("endDt1", endDt);
//				query.setParameter("endDt2", endDt);
				
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public int queryCount(final List<String> lonelyManIds, final List<String> memberIds,
			final Date startDt, final Date endDt) {
		List<Long> list = (List<Long>) getHibernateTemplate().execute(new HibernateCallback<List<Long>>() {
			@Override
			public List<Long> doInHibernate(Session session)
					throws HibernateException {
				String hql = "select count(vo.helpId) from LonelyHelpVO vo where 1 = 1 ";
				if(!lonelyManIds.isEmpty()) {
					hql += "and (vo.lonelyManId in (:manId1) or :manId2 is null) ";
				}
				if(!memberIds.isEmpty()) {
					hql += "and (vo.deliverer in (:memberId1) or :memberId2 is null) ";
				}
//				hql +="and (vo.createTime >= :startDt1 or :startDt2 is null) ";
//				hql +="and (vo.createTime <= :endDt1 or :endDt2 is null) ";
				hql +="and (vo.createTime between :startDt and :endDt) ";
				hql += " order by vo.createTime Desc";
				
				Query query = session.createQuery(hql);
				if(!lonelyManIds.isEmpty()) {
					query.setParameterList("manId1", lonelyManIds);
					query.setParameter("manId2", lonelyManIds.isEmpty() ? null : lonelyManIds.toString());
				}
				if(!memberIds.isEmpty()) {
					query.setParameterList("memberId1", memberIds);
					query.setParameter("memberId2", memberIds.isEmpty() ? null : memberIds.toString());
				}
//				query.setParameter("startDt1", startDt);
//				query.setParameter("startDt2", startDt);
//				query.setParameter("endDt1", endDt);
//				query.setParameter("endDt2", endDt);
				
				if(null == startDt) {
					query.setParameter("startDt", DateUtil.parse("1900-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				} else {
					query.setParameter("startDt", startDt);
				}
				if(null == endDt) {
					query.setParameter("endDt", new Date());
				} else {
					query.setParameter("endDt", endDt);
				}
				
				return query.list();
			}
		});
		return list.get(0).intValue();
	}
}
