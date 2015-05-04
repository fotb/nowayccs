package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.dao.ILonelyManInfoDAO;
import com.ccs.dao.IPartyMemberForLonelyDAO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;

@Service("lonelyFamilyBO")
public class LonelyFamilyBOImpl implements ILonelyFamilyBO {
	
	@Autowired
	private ILonelyManInfoDAO lonelyManInfoDAO;
	
	@Autowired
	private IPartyMemberForLonelyDAO partyMemberForLonelyDAO;

	@Override
	public LonelyManInfoVO findLonelyManInfo(String telPhone) {
		List<LonelyManInfoVO> list = lonelyManInfoDAO.findByTelphone(telPhone);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<PartyMemberForLonelyVO> findByManId(String manId) {
		return partyMemberForLonelyDAO.findByManId(manId);
	}

}
