package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizLifeBO;
import com.ccs.dao.IEntSrvCountDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IVolunteerSrvCountDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;

@Service
public class BizLifeBOImpl implements IBizLifeBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Autowired
	private ILifeInformationDAO lifeInformationDAO;
	
	@Autowired
	private IVolunteerSrvCountDAO volunteerSrvCountDAO;
	
	@Autowired
	private IEntSrvCountDAO entSrvCountDAO;
	
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
	public Map<String, String> getVltSrvCount(String startDt, String endDt, String creator) {
		return volunteerSrvCountDAO.getVolunteerSrvCount(startDt, endDt, creator);
	}

	@Override
	public Map<String, String> getEntSrvCount(String startDt, String endDt, String creator) {
		return entSrvCountDAO.getEntSrvCount(startDt, endDt, creator);
	}

	@Override
	@Transactional
	public void deliverLife(InformationVO infoVO, LifeInformationVO vo) {
		lifeInformationDAO.saveOrUpdate(vo);
		
		infoVO.setStatus(Constants.SYS_INFOMATION_STATES_CLZ);
		informationDAO.saveOrUpate(infoVO);
	}

	@Override
	@Transactional
	public void del(String infoId, String userId) {
		InformationVO vo = informationDAO.findById(infoId);
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YQX);
		vo.setCanceler(userId);
		vo.setCancelTime(new Date());
		informationDAO.saveOrUpate(vo);
	}

	@Override
	public LifeInformationVO findLifeInfoByInfoId(String infoId) {
		return lifeInformationDAO.findByInfoId(infoId);
	}
}
