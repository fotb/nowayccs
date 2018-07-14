package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.bean.TrafficSearchBean;
import com.ccs.bean.UserTrafficBean;
import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IUserDAO;
import com.ccs.util.DateUtil;
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
		
		return (List<UserVO>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session s)
							throws HibernateException {
						Query query = s.createQuery("from UserVO vo order by vo.loginName");
						query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserVO findByLoginNameAndPwd(String loginName, String pwd) {
		List<UserVO> list = (List<UserVO>) getHibernateTemplate()
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
		return (List<UserVO>) getHibernateTemplate().find("from UserVO vo where vo.loginName = ?", loginName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findAllOnJob() {
		return (List<UserVO>) getHibernateTemplate().find("from UserVO vo where vo.onJob = ?", UserVO.ONJOB_YES);
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
		return (List<UserVO>) getHibernateTemplate().find(hqlBuffer.toString(), paramList.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVO> findAll() {
		return (List<UserVO>) getHibernateTemplate().find("from UserVO vo");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserTrafficBean> findUserTraffic(TrafficSearchBean bean) {
		List<Object> objs = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("select new com.ccs.bean.UserTrafficBean(u.userId, u.loginName, u.userName, count(i.infoId) as traffic) from UserVO u, InformationVO i ");
		buffer.append("where i.creator = u.userId ");
		buffer.append("and (u.loginName = ? or ? is null) ");
		objs.add(bean.getLoginName());
		objs.add(bean.getLoginName());
		buffer.append("and (trunc(i.createTime) >= ? or ? is null) ");
		objs.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		objs.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		buffer.append("and (trunc(i.createTime) <= ? or ? is null) ");
		objs.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		objs.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		buffer.append("and i.helpType = ?" );
		objs.add(bean.getHelpType());
		buffer.append("group by u.loginName, u.userId, u.userName");
		return (List<UserTrafficBean>) getHibernateTemplate().find(buffer.toString(), objs.toArray());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserTrafficBean> findUserJdjtTraffic(TrafficSearchBean bean) {
		List<Object> objs = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("select new com.ccs.bean.UserTrafficBean(u.userId, u.loginName, u.userName, count(i.helpId) as traffic) from UserVO u, LonelyHelpVO i ");
		buffer.append("where i.creator = u.userId ");
		buffer.append("and (u.loginName = ? or ? is null) ");
		objs.add(bean.getLoginName());
		objs.add(bean.getLoginName());
		buffer.append("and (trunc(i.createTime) >= ? or ? is null) ");
		objs.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		objs.add(DateUtil.parse(bean.getStartDt(), "yyyy-MM-dd"));
		buffer.append("and (trunc(i.createTime) <= ? or ? is null) ");
		objs.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		objs.add(DateUtil.parse(bean.getEndDt(), "yyyy-MM-dd"));
		buffer.append("group by u.loginName, u.userId, u.userName");
		return (List<UserTrafficBean>) getHibernateTemplate().find(buffer.toString(), objs.toArray());
	}
	
}
