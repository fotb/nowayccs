package com.ccs.dao.impl;

import java.util.ArrayList;
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
		final List<ReferInformationVO> list = (List<ReferInformationVO>) getHibernateTemplate().find(
				"from ReferInformationVO t where t.infoId = ?", infoId);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReferInformationVO> findByInfoIds(List<String> infoIds) {
		if(infoIds.isEmpty()) {
			return new ArrayList<ReferInformationVO>();
		} else {
			StringBuffer hqlBuffer = new StringBuffer(1000);
			hqlBuffer.append("from ReferInformationVO t where t.infoId in (");
			
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
			return (List<ReferInformationVO>) getHibernateTemplate().find(hqlBuffer.toString(), infoIds.toArray());
		}
	}

}
