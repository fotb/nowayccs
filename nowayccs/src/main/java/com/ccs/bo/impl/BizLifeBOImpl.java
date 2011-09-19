package com.ccs.bo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IBizLifeBO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.IVolunteerSrvCountDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

@Service
public class BizLifeBOImpl implements IBizLifeBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Autowired
	private IVolunteerSrvCountDAO volunteerSrvCountDAO;
	
	@Override
	public List<InformationVO> findByCreatorAndStatus(String userId,
			String status, String helpType, PageInfo pageInfo) {
		return informationDAO.findByCreatorAndStatus(userId, status, helpType, pageInfo);
	}

	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}

	@Override
	public Map<String, String> getVltSrvCount() {
		return volunteerSrvCountDAO.getVolunteerSrvCount();
	}

	@Override
	public Map<String, String> getVltSrvCountToday() {
		return volunteerSrvCountDAO.getVolunteerSrvCountToday();
	}

}
