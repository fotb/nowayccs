package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.vo.AffairInformationVO;

@Repository("affaireInformationDAO")
public class AffairInformationDAOImpl extends DefaultDAOSupport implements
		IAffairInformationDAO {

	@Override
	public void saveOrUpdate(AffairInformationVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(AffairInformationVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public AffairInformationVO findById(String affairInfoId) {
		return getHibernateTemplate().get(AffairInformationVO.class,
				affairInfoId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AffairInformationVO findByInfoId(String infoId) {
		final List<AffairInformationVO> list = getHibernateTemplate().find(
				"from AffairInformationVO t where t.infoId = ?", infoId);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AffairInformationVO> findByInfoIds(List<String> infoIds) {
		StringBuffer hqlBuffer = new StringBuffer(1000);
		hqlBuffer.append("from AffairInformationVO where t.infoId in (");
		
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
