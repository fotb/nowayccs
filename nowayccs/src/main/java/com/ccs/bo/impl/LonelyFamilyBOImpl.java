package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.bo.IUserBO;
import com.ccs.dao.ILonelyHelpDAO;
import com.ccs.dao.ILonelyManInfoDAO;
import com.ccs.dao.IPartyMemberForLonelyDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LfMgrForm;
import com.ccs.web.domain.ShsForm;
import com.ccs.web.domain.ShsResultDTO;

@Service("lonelyFamilyBO")
@Transactional
public class LonelyFamilyBOImpl implements ILonelyFamilyBO {
	
	@Autowired
	private ILonelyManInfoDAO lonelyManInfoDAO;
	
	@Autowired
	private IPartyMemberForLonelyDAO partyMemberForLonelyDAO;

	@Autowired
	private ILonelyHelpDAO lonelyHelpDAO;
	
	@Autowired
	private IUserBO userBO;
	
	
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
	@Transactional
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
		Date endDt = shsForm.getEndDt() == null ? null : DateUtil.parse(shsForm.getEndDt() + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
		List<LonelyHelpVO> voList = lonelyHelpDAO.query(manIdList, memberIds, startDt, endDt, pageInfo);
		
		List<ShsResultDTO> dtoList = new ArrayList<ShsResultDTO>();
		for (LonelyHelpVO lonelyHelpVO : voList) {
			ShsResultDTO dto = new ShsResultDTO();
			dto.setLhVO(lonelyHelpVO);
			dto.setLmiVO(lonelyManInfoDAO.findById(lonelyHelpVO.getLonelyManId()));
			dto.setPmflVO(partyMemberForLonelyDAO.findById(lonelyHelpVO.getDeliverer()));
			UserVO userVO = userBO.findById(lonelyHelpVO.getCreator());
			dto.setUserId(userVO.getUserId());
			dto.setUserName(userVO.getUserName());
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
		Date endDt = shsForm.getEndDt() == null ? null : DateUtil.parse(shsForm.getEndDt() + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
		
		return lonelyHelpDAO.queryCount(manIdList, memberIds, startDt, endDt);		
	}

	@Override
	public LonelyHelpVO findLonelyHelpByHelpId(String helpId) {
		return lonelyHelpDAO.findById(helpId);
	}

	@Override
	public LonelyManInfoVO findLonelyManInfoByManId(String manId) {
		return lonelyManInfoDAO.findById(manId);
	}

	@Override
	public PartyMemberForLonelyVO findPartyMemberForLonelyById(String memberId) {
		return partyMemberForLonelyDAO.findById(memberId);
	}

	@Override
	public List<LonelyManInfoVO> queryLonelyManInfo(LfMgrForm lfMgrForm,
			PageInfo pageInfo) {
		pageInfo.setTotalRecords(lonelyManInfoDAO.countManInfo(lfMgrForm));
		return lonelyManInfoDAO.queryManInfo(lfMgrForm, pageInfo);
	}

	@Override
	@Transactional
	public void saveOrUpdate(LonelyManInfoVO lmiVO) {
		lonelyManInfoDAO.saveOrUpdate(lmiVO);
	}

	@Override
	@Transactional
	public void saveOrUpdate(PartyMemberForLonelyVO pmVO) {
		partyMemberForLonelyDAO.saveOrUpdate(pmVO);
	}

	@Override
	@Transactional
	public void pmDel(String memberId) {
		PartyMemberForLonelyVO pmVO = partyMemberForLonelyDAO.findById(memberId);
		partyMemberForLonelyDAO.delete(pmVO);
	}

	@Override
	@Transactional
	public void delLonelyFamily(String manId) {
		List<PartyMemberForLonelyVO> pmList = partyMemberForLonelyDAO.findByManId(manId);
		for (PartyMemberForLonelyVO partyMemberForLonelyVO : pmList) {
			partyMemberForLonelyDAO.delete(partyMemberForLonelyVO);
		}
		LonelyManInfoVO lmiVO = lonelyManInfoDAO.findById(manId);
		lonelyManInfoDAO.delete(lmiVO);
	}
}
