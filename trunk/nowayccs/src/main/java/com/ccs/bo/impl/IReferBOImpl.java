package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IReferBO;
import com.ccs.dao.IReferDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.ReferVO;

@Service("referBO")
public class IReferBOImpl implements IReferBO {

	@Autowired
	private IReferDAO referDAO;
	
	@Override
	public void saveoOrUpdate(ReferVO vo) {
		referDAO.saveoOrUpdate(vo);
	}

	@Override
	public void delete(ReferVO vo) {
		referDAO.delete(vo);
	}

	@Override
	public ReferVO findById(String referId) {
		return referDAO.findById(referId);
	}

	@Override
	public List<ReferVO> findByParams(String title, String referType,
			PageInfo pageInfo) {
		pageInfo.setTotalRecords(referDAO.getCountByParams(title, referType));
		return referDAO.findByParams(title, referType, pageInfo);
	}

}
