package com.ccs.dao.impl;

import java.util.ArrayList;
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
		final List<AffairInformationVO> list = (List<AffairInformationVO>) getHibernateTemplate().find(
				"from AffairInformationVO t where t.infoId = ?", infoId);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AffairInformationVO> findByInfoIds(List<String> infoIds) {
		if(infoIds.isEmpty()) {
			return new ArrayList<AffairInformationVO>();
		} else {
			StringBuffer hqlBuffer = new StringBuffer(1000);
			hqlBuffer.append("from AffairInformationVO t where t.infoId in (");
			
			for (int i = 0; i < infoIds.size(); i++) {
				if((i % 1000 ) == 0 && i > 0) {
					hqlBuffer.deleteCharAt(hqlBuffer.length() - 1);
					hqlBuffer.append(") OR t.infoId IN ( ?,");
				} else {
					hqlBuffer.append("?,");
				}
				
			}
			hqlBuffer.deleteCharAt(hqlBuffer.length() - 1);
			hqlBuffer.append(")");
			return (List<AffairInformationVO>) getHibernateTemplate().find(hqlBuffer.toString(), infoIds.toArray());
		}
	}

}
