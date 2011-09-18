package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IUserDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.UserVO;

@Repository("userDAO")
public class UserDAOImpl extends DefaultDAOSupport implements IUserDAO {

	@Override
	public void saveOrUpdate(UserVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void update(UserVO vo) {
		getHibernateTemplate().update(vo);
	}
	
	@Override
	public void delete(UserVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public UserVO findById(String userId) {
		return getHibernateTemplate().get(UserVO.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findAll(final PageInfo pageInfo) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						Query query = s
								.createQuery("from UserVO vo order by vo.loginName");
						query.setFirstResult((pageInfo.getCurrentPage() - 1)
								* pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserVO findByLoginNameAndPwd(String loginName, String pwd) {
		List<UserVO> list = getHibernateTemplate()
				.find("from UserVO vo where vo.loginName = ? and vo.loginPassword = ? and vo.onJob = ?",
						loginName, pwd, UserVO.ONJOB_YES);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public int getTotalCount() {
		String hql = "select count(*) from UserVO vo";
		Long count = (Long) getHibernateTemplate().find(hql).listIterator()
				.next();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findByLoginName(String loginName) {
		return getHibernateTemplate().find("from UserVO vo where vo.loginName = ?", loginName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findAllOnJob() {
		return getHibernateTemplate().find("from UserVO vo where vo.onJob = ?", UserVO.ONJOB_YES);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findByUserIds(List<String> userIdList) {
		StringBuffer hqlBuffer = new StringBuffer(1000);
		hqlBuffer.append("from UserVO t where t.userId in (");
		
		for (int i = 0; i < userIdList.size(); i++) {
			if(i == userIdList.size() - 1) {
				hqlBuffer.append("? )");
			} else {
				hqlBuffer.append("?, ");
			}
		}
		hqlBuffer.append(" and t.onJob = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.addAll(userIdList);
		paramList.add(UserVO.ONJOB_YES);
		return getHibernateTemplate().find(hqlBuffer.toString(), paramList.toArray());
	}
}
