package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.vo.LifeInformationVO;

@Repository("lifeInformationDAO")
public class LifeInformationDAOImpl extends DefaultDAOSupport implements
		ILifeInformationDAO {

	@Override
	public void saveOrUpdate(LifeInformationVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(LifeInformationVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public LifeInformationVO findById(String affairInfoId) {
		return getHibernateTemplate().get(LifeInformationVO.class,
				affairInfoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public LifeInformationVO findByInfoId(String infoId) {
		final List<LifeInformationVO> list = getHibernateTemplate().find(
				"from LifeInformationVO t where t.infoId = ?", infoId);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LifeInformationVO> findByInfoIds(List<String> infoIds) {
		StringBuffer hqlBuffer = new StringBuffer(1000);
		hqlBuffer.append("from LifeInformationVO t where t.infoId in (");
		
		for (int i = 0; i < infoIds.size(); i++) {
			if(i == infoIds.size() - 1) {
				hqlBuffer.append("? )");
			} else {
				hqlBuffer.append("?, ");
			}
		}
		return getHibernateTemplate().find(hqlBuffer.toString(), infoIds.toArray());
	}

}
