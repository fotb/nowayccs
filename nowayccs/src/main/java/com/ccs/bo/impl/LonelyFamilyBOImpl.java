package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.dao.ILonelyHelpDAO;
import com.ccs.dao.ILonelyManInfoDAO;
import com.ccs.dao.IPartyMemberForLonelyDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.web.domain.ShsForm;
import com.ccs.web.domain.ShsResultDTO;

@Service("lonelyFamilyBO")
public class LonelyFamilyBOImpl implements ILonelyFamilyBO {
	
	@Autowired
	private ILonelyManInfoDAO lonelyManInfoDAO;
	
	@Autowired
	private IPartyMemberForLonelyDAO partyMemberForLonelyDAO;

	@Autowired
	private ILonelyHelpDAO lonelyHelpDAO;
	
	@Override
	public LonelyManInfoVO findLonelyManInfo(String telPhone) {
		List<LonelyManInfoVO> list = lonelyManInfoDAO.findByTelphone(telPhone);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<PartyMemberForLonelyVO> findByManId(String manId) {
		return partyMemberForLonelyDAO.findByManId(manId);
	}

	@Override
	public void helpSave(LonelyHelpVO vo) {
		lonelyHelpDAO.saveOrUpdate(vo);
	}

	@Override
	public List<ShsResultDTO> findSpecialHelp(ShsForm shsForm, PageInfo pageInfo) {
		pageInfo.setTotalRecords(countSpecialHelp(shsForm));
		//query lonelymaninfo and telphone, get lonelymanids
		List<String> manIdList = new ArrayList<String>();
		if(!StringUtil.isNull(shsForm.getSpecialName()) || !StringUtil.isNull(shsForm.getTelphone())) {
			manIdList = lonelyManInfoDAO.findLonelyManIds(shsForm.getSpecialName(), shsForm.getTelphone());
		}
		
		//query partmember
		List<String> memberIds = new ArrayList<String>();
		if(!StringUtil.isNull(shsForm.getMemberName())) {
			memberIds = partyMemberForLonelyDAO.findMemberIds(shsForm.getMemberName());
		}
		
		Date startDt = shsForm.getStartDt() == null ? null : DateUtil.parse(shsForm.getStartDt(), "yyyy-MM-dd");
		Date endDt = shsForm.getEndDt() == null ? null : DateUtil.parse(shsForm.getEndDt(), "yyyy-MM-dd");
		List<LonelyHelpVO> voList = lonelyHelpDAO.query(manIdList, memberIds, startDt, endDt, pageInfo);
		
		List<ShsResultDTO> dtoList = new ArrayList<ShsResultDTO>();
		for (LonelyHelpVO lonelyHelpVO : voList) {
			ShsResultDTO dto = new ShsResultDTO();
			dto.setLhVO(lonelyHelpVO);
			dto.setLmiVO(lonelyManInfoDAO.findById(lonelyHelpVO.getLonelyManId()));
			dto.setPmflVO(partyMemberForLonelyDAO.findById(lonelyHelpVO.getDeliverer()));
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public int countSpecialHelp(ShsForm shsForm) {
		//query lonelymaninfo and telphone, get lonelymanids
		List<String> manIdList = new ArrayList<String>();
		if(!StringUtil.isNull(shsForm.getSpecialName()) || !StringUtil.isNull(shsForm.getTelphone())) {
			manIdList = lonelyManInfoDAO.findLonelyManIds(shsForm.getSpecialName(), shsForm.getTelphone());
		}
		
		//query partmember
		List<String> memberIds = new ArrayList<String>();
		if(!StringUtil.isNull(shsForm.getMemberName())) {
			memberIds = partyMemberForLonelyDAO.findMemberIds(shsForm.getMemberName());
		}
		
		Date startDt = shsForm.getStartDt() == null ? null : DateUtil.parse(shsForm.getStartDt(), "yyyy-MM-dd");
		Date endDt = shsForm.getEndDt() == null ? null : DateUtil.parse(shsForm.getEndDt(), "yyyy-MM-dd");
		
		return lonelyHelpDAO.queryCount(manIdList, memberIds, startDt, endDt);		
	}
}
