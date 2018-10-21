package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ISmsRecvDAO;
import com.ccs.vo.SmsRecvVO;

@Repository("smsRecvDAO")
public class SmsRecvDAOImpl extends DefaultDAOSupport implements ISmsRecvDAO {

	@Override
	public void saveOrUpdate(SmsRecvVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(SmsRecvVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public SmsRecvVO findById(String smsId) {
		return getHibernateTemplate().get(SmsRecvVO.class, smsId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsRecvVO> findByStatus(String status) {
		return (List<SmsRecvVO>) getHibernateTemplate().find("from SmsRecvVO t where t.status = ? order by t.recvDt", status);
	}

	@Override
	public void saveOrUpdateAll(List<SmsRecvVO> list) {
		for (SmsRecvVO smsRecvVO : list) {
			getHibernateTemplate().saveOrUpdate(smsRecvVO);
		}
	}

}
