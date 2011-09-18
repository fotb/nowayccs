package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IBizLifeBO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

@Service
public class BizLifeBOImpl implements IBizLifeBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Override
	public List<InformationVO> findByCreatorAndStatus(String userId,
			String status, String helpType, PageInfo pageInfo) {
		return informationDAO.findByCreatorAndStatus(userId, status, helpType, pageInfo);
	}

	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}

}
