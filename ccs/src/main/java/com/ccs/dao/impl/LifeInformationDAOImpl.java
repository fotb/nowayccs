package com.ccs.dao.impl;

import java.util.ArrayList;
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
		final List<LifeInformationVO> list = (List<LifeInformationVO>) getHibernateTemplate().find(
				"from LifeInformationVO t where t.infoId = ?", infoId);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LifeInformationVO> findByInfoIds(List<String> infoIds) {
		if(infoIds.isEmpty()) {
			return new ArrayList<LifeInformationVO>();
		} else {
			StringBuffer hqlBuffer = new StringBuffer(1000);
			hqlBuffer.append("from LifeInformationVO t where t.infoId in (");
					
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
			return (List<LifeInformationVO>) getHibernateTemplate().find(hqlBuffer.toString(), infoIds.toArray());
		}
	}

}
