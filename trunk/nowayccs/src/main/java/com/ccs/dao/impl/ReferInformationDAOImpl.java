package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IReferInformationDAO;
import com.ccs.vo.ReferInformationVO;

@Repository("referInformationDAO")
public class ReferInformationDAOImpl extends DefaultDAOSupport implements
		IReferInformationDAO {

	@Override
	public void saveOrUpdate(ReferInformationVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(ReferInformationVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public ReferInformationVO findById(String affairInfoId) {
		return getHibernateTemplate().get(ReferInformationVO.class,
				affairInfoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReferInformationVO findByInfoId(String infoId) {
		final List<ReferInformationVO> list = getHibernateTemplate().find(
				"from ReferInformationVO t where t.infoId = ?", infoId);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReferInformationVO> findByInfoIds(List<String> infoIds) {
		StringBuffer hqlBuffer = new StringBuffer(1000);
		hqlBuffer.append("from ReferInformationVO t where t.infoId in (");
		
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
