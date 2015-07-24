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
import com.ccs.dao.ILonelyManInfoDAO;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.web.domain.LfMgrForm;

@Repository("lonelyManInfoDAO")
public class LonelyManInfoDAOImpl extends DefaultDAOSupport implements
		ILonelyManInfoDAO {

	@Override
	public void saveOrUpdate(LonelyManInfoVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(LonelyManInfoVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public LonelyManInfoVO findById(String manId) {
		return getHibernateTemplate().get(LonelyManInfoVO.class, manId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LonelyManInfoVO> findByTelphone(String telphone) {
		return getHibernateTemplate().find("from LonelyManInfoVO vo where vo.telphone like ?", "%" + telphone + "%");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findLonelyManIds(String lonelyManName, String telphone) {
		List<String> list = new ArrayList<String>();
		list.add("%" + lonelyManName + "%");
		list.add(StringUtil.emptyToNull(lonelyManName));
		list.add("%" + telphone + "%");
		list.add(StringUtil.emptyToNull(telphone));
		
		return getHibernateTemplate().find("select manId from LonelyManInfoVO vo where (vo.manName like ? or ? is null) and (vo.telphone like ? or ? is null)", list.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LonelyManInfoVO> queryManInfo(LfMgrForm lfMgrForm,
			final PageInfo pageInfo) {
		
		final List<String> valList = new ArrayList<String>();
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getManName()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getManName()));
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getTelphone()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getTelphone()));
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getArea()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getArea()));
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getAddress()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getAddress()));


		
		final StringType[] types = new StringType[valList.size()];
		for (int i = 0; i < valList.size(); i++) {
			types[i] = StandardBasicTypes.STRING;
		}
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from LonelyManInfoVO vo where 1 = 1 ";
				hql += " and (vo.manName like ? or ? is null)";
				hql += " and (vo.telphone like ? or ? is null)";
				hql += " and (vo.area like ? or ? is null)";
				hql += " and (vo.address like ? or ? is null)";
				
				Query query = session.createQuery(hql);
				query.setParameters(valList.toArray(), types);
				
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countManInfo(LfMgrForm lfMgrForm) {
		final List<String> valList = new ArrayList<String>();
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getManName()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getManName()));
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getTelphone()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getTelphone()));
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getArea()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getArea()));
		valList.add(StringUtil.emptyToNull("%" + lfMgrForm.getAddress()) + "%");
		valList.add(StringUtil.emptyToNull(lfMgrForm.getAddress()));

		
		final StringType[] types = new StringType[valList.size()];
		for (int i = 0; i < valList.size(); i++) {
			types[i] = StandardBasicTypes.STRING;
		}
		
		List<Long> list = getHibernateTemplate().executeFind(new HibernateCallback<List<InformationVO>>() {
			@Override
			public List<InformationVO> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "select count(vo.manId) from LonelyManInfoVO vo where 1 = 1 ";
				hql += " and (vo.manName like ? or ? is null)";
				hql += " and (vo.telphone like ? or ? is null)";
				hql += " and (vo.area like ? or ? is null)";
				hql += " and (vo.address like ? or ? is null)";
				
				Query query = session.createQuery(hql);
				query.setParameters(valList.toArray(), types);
				
				return query.list();
			}
		});
		return list.get(0).intValue();
	}
}
