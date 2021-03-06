package com.ccs.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IBlackListDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.BlackListVO;

@Repository("blackListDAO")
public class BlackListDAOImpl extends DefaultDAOSupport implements
		IBlackListDAO {

	@Override
	public void saveOrUpdate(BlackListVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(BlackListVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public BlackListVO findById(String phoneId) {
		return getHibernateTemplate().get(BlackListVO.class, phoneId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BlackListVO findByPhoneNum(String phoneNum) {
		List<BlackListVO> list = (List<BlackListVO>) getHibernateTemplate().find("from BlackListVO t where trim(t.phoneNum) = ?", phoneNum);
		if(!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<BlackListVO> findByParams(final String phoneNum, final String levels, final PageInfo pageInfo) {
		return (List<BlackListVO>) getHibernateTemplate().execute(new HibernateCallback<List<BlackListVO>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<BlackListVO> doInHibernate(Session session)
					throws HibernateException {
				StringBuffer buffer = new StringBuffer(1000);
				buffer.append("from BlackListVO t where ");
				buffer.append("(t.phoneNum like ? or ? is null) ");
				buffer.append("and (t.levels = ? or ? is null)");
				Query query = session.createQuery(buffer.toString());
				query.setParameter(0, "%" + phoneNum + "%");
				query.setParameter(1, phoneNum);
				query.setParameter(2, levels);
				query.setParameter(3, levels);
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@Override
	public int getTotalByParams(String phoneNum, String levels) {
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("select count(t.phoneId) from BlackListVO t where ");
		buffer.append("(t.phoneNum like ? or ? is null) ");
		buffer.append("and (t.levels = ? or ? is null)");
		final Long count = (Long) getHibernateTemplate().find(buffer.toString(), new Object[]{"%" + phoneNum + "%", phoneNum, levels, levels}).listIterator().next();
		return count.intValue();
	}

}
