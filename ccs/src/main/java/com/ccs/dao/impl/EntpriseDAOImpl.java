package com.ccs.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IEntpriseDAO;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.EntpriseVO;

@Repository("entpriseDAO")
public class EntpriseDAOImpl extends DefaultDAOSupport implements IEntpriseDAO {

	@Override
	public EntpriseVO findById(String entpriseId) {
		return getHibernateTemplate().get(EntpriseVO.class, entpriseId);
	}

	@Override
	public void saveOrUpdate(EntpriseVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(EntpriseVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public int getTotalCount() {
		Long count = (Long) getHibernateTemplate()
				.find("select count(entpriseId) from EntpriseVO vo")
				.listIterator().next();
		return count.intValue();
	}

	@Override
	public List<EntpriseVO> findAll(final PageInfo pageInfo) {
		return (List<EntpriseVO>) getHibernateTemplate().execute(
				new HibernateCallback<List<EntpriseVO>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<EntpriseVO> doInHibernate(Session session)
							throws HibernateException {
						Query query = session
								.createQuery("from EntpriseVO vo order by vo.servicsType, vo.entpriseNo");
						query.setFirstResult((pageInfo.getCurrentPage() - 1)
								* pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
					}
				});
	}

	private static final String tempSql = "Select Distinct(t.entpriseId) as entpriseId, "
			+ "t.entpriseNo, t.entpriseName, t.address, t.lawDeputy, t.regMoney, t.serviceTime, t.linkTel, t.serviceTel, "
			+ "t.memberSign, t.registerCode, t.taxCode, t.dealRange, t.linkName, t.servicesType, t.status "
			+ "From hj_Entprise t, hj_ClassOfEntprise b "
			+ "Where t.entpriseId = b.entpriseId ";
	private static final String tempSql2 = "(t.entpriseName like ? or ? is null) and (t.entpriseNo=? or ? is null) "
			+ "and (t.status=? or ? is null) and (t.servicesType = ? or ? is null) and (t.address like ? or ? is null) order by cast(t.entpriseNo as int)";
	private static final String sql = tempSql
			+ "And b.entclassId In ( "
			+ "Select entclassid From hj_entclass a Start With a.parententclassid = ? CONNECT BY Prior a.entclassid = a.parententclassid"
			+ ") and " + tempSql2;
	private static final String sql2 = tempSql + "And b.entclassId = ? and "
			+ tempSql2;
	private static final String sql3 = "select t.* from hj_entprise t where " + tempSql2;
	
	private static final String countSql1 = "select count(DISTINCT(t.entpriseId)) from hj_entprise t, hj_ClassOfEntprise b "
			+ "where t.entpriseId = b.entpriseId "
			+ "And b.entclassId In ( "
			+ "Select entclassid From hj_entclass a Start With a.parententclassid = ? CONNECT BY Prior a.entclassid = a.parententclassid"
			+ ") and " + tempSql2;
	
	private static final String countSql2 = "select count(DISTINCT(t.entpriseId)) from hj_Entprise t, hj_ClassOfEntprise b "
		+ "where t.entpriseId = b.entpriseId "
		+ "And b.entclassId = ? and " + tempSql2;
	
	private static final String countSql3 = "select count(t.entpriseId) from hj_Entprise t where " + tempSql2;

	/**
	 * bigEntClassID,smallEntClassID,entClassID,: ���Ϊ�գ�����Բ�ѯ����
	 */
	public List<EntpriseVO> findByParams(final String entpriseName,
			final String entpriseNo, final String servicesType, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, final String address, final PageInfo pageInfo) {
		return (List<EntpriseVO>) getHibernateTemplate().execute(
				new HibernateCallback<List<EntpriseVO>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<EntpriseVO> doInHibernate(Session session)
							throws HibernateException {

						final List<Object> objList = new ArrayList<Object>();
						String targetSql = "";
						if (!StringUtil.isNull(entclassId)) {
							objList.add(entclassId);
							targetSql = sql2;
						} else {
							if (!StringUtil.isNull(smallEntclassId)) {
								objList.add(smallEntclassId);
								targetSql = sql;

							} else {
								if (!StringUtil.isNull(bigEntclassId)) {
									objList.add(bigEntclassId);
									targetSql = sql;
								} else {
									targetSql = sql3;
								}
							}
						}

						
						objList.add("%" + entpriseName + "%");
						objList.add(entpriseName);
						objList.add(entpriseNo);
						objList.add(entpriseNo);
						objList.add(status);
						objList.add(status);
						objList.add(servicesType);
						objList.add(servicesType);
						objList.add("%" + address + "%");
						objList.add(address);
						
						final StringType[] types = new StringType[objList.size()];
						for (int i = 0; i < objList.size(); i++) {
							types[i] = StandardBasicTypes.STRING;
						}

						Query query = session.createSQLQuery(targetSql).addEntity(EntpriseVO.class);
						query.setParameters(objList.toArray(), types);
						query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
					}
				});
	}

	@Override
	public int getTotalCount(final String entpriseName,
			final String entpriseNo, final String servicesType, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, final String address) {
		BigDecimal count = (BigDecimal) getHibernateTemplate().execute(
				new HibernateCallback<BigDecimal>() {
					@Override
					public BigDecimal doInHibernate(Session session)
							throws HibernateException {
						final List<Object> objList = new ArrayList<Object>();
						String targetSql = "";
						if (!StringUtil.isNull(entclassId)) {
							objList.add(entclassId);
							targetSql = countSql2;
						} else {
							if (!StringUtil.isNull(smallEntclassId)) {
								objList.add(smallEntclassId);
								targetSql = countSql1;

							} else {
								if (!StringUtil.isNull(bigEntclassId)) {
									objList.add(bigEntclassId);
									targetSql = countSql1;
								} else {
									targetSql = countSql3;
								}
							}
						}

						
						objList.add("%" + entpriseName + "%");
						objList.add(entpriseName);
						objList.add(entpriseNo);
						objList.add(entpriseNo);
						objList.add(status);
						objList.add(status);
						objList.add(servicesType);
						objList.add(servicesType);
						objList.add("%" + address + "%");
						objList.add(address);
						
						final StringType[] types = new StringType[objList
								.size()];
						for (int i = 0; i < objList.size(); i++) {
							types[i] = StandardBasicTypes.STRING;
						}

						Query query = session.createSQLQuery(targetSql);
						query.setParameters(objList.toArray(), types);
						return (BigDecimal) query.list().get(0);
					}
				});
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntpriseVO> findAll() {
		return (List<EntpriseVO>) getHibernateTemplate().find("from EntpriseVO ");
	}

}
