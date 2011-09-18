package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IVolunteerDAO;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.VolunteerVO;

@Repository("volunteerDAO")
public class VolunteerDAOImpl extends DefaultDAOSupport implements
		IVolunteerDAO {

	@Override
	public void saveOrUpate(VolunteerVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(VolunteerVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public VolunteerVO findById(String volunteerId) {
		return getHibernateTemplate().get(VolunteerVO.class, volunteerId);
	}

	private static final String hql = "from VolunteerVO t where (t.status = ? or ? is null) and (t.serviceType = ? or ? is null) and "
			+ "(t.areaId = ? or ? is null) and (t.areaSubId = ? or ? is null) and (t.volunteerNo = ? or ? is null) ";

	@SuppressWarnings("unchecked")
	@Override
	public List<VolunteerVO> findByParams(String status, String serviceType,
			String areaId, String areaSubId, String volunteerNo,
			final PageInfo pageInfo) {
		final List<String> valList = new ArrayList<String>();
		valList.add(StringUtil.emptyToNull(status));
		valList.add(StringUtil.emptyToNull(status));
		valList.add(StringUtil.emptyToNull(serviceType));
		valList.add(StringUtil.emptyToNull(serviceType));
		valList.add(StringUtil.emptyToNull(areaId));
		valList.add(StringUtil.emptyToNull(areaId));
		valList.add(StringUtil.emptyToNull(areaSubId));
		valList.add(StringUtil.emptyToNull(areaSubId));
		valList.add(StringUtil.emptyToNull(volunteerNo));
		valList.add(StringUtil.emptyToNull(volunteerNo));
		
		final StringType[] types = new StringType[valList.size()];
		for (int i = 0; i < valList.size(); i++) {
			types[i] = StandardBasicTypes.STRING;
		}
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<VolunteerVO>>() {
			@Override
			public List<VolunteerVO> doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql + "order by cast(t.volunteerNo as int)");
				query.setParameters(valList.toArray(), types);
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@Override
	public int getCountByParams(String status, String serviceType,
			String areaId, String areaSubId, String volunteerNo) {
		final String targetHql = "select count(t.volunteerId) " + hql;
		final Object[] objs = new Object[]{status, status, serviceType, serviceType, areaId, areaId, areaSubId, areaSubId, volunteerNo, volunteerNo};
		final Long count = (Long) getHibernateTemplate().find(targetHql, objs).listIterator().next();
		return count.intValue();
	}

}
