package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.web.domain.LfMgrForm;
import com.ccs.web.domain.ShsForm;
import com.ccs.web.domain.ShsResultDTO;

public interface ILonelyFamilyBO {
	LonelyManInfoVO findLonelyManInfo(final String telPhone);
	List<PartyMemberForLonelyVO> findByManId(final String manId);
	void helpSave(LonelyHelpVO vo);
	
	List<ShsResultDTO> findSpecialHelp(ShsForm shsForm, PageInfo pageInfo); 
	
	int countSpecialHelp(ShsForm shsForm);
	
	LonelyHelpVO findLonelyHelpByHelpId(final String helpId);
	
	LonelyManInfoVO findLonelyManInfoByManId(final String manId);
	
	PartyMemberForLonelyVO findPartyMemberForLonelyById(final String memberId);
	
	List<LonelyManInfoVO> queryLonelyManInfo(final LfMgrForm lfMgrForm, final PageInfo pageInfo);
	
	void saveOrUpdate(final LonelyManInfoVO lmiVO);
	
	void saveOrUpdate(final PartyMemberForLonelyVO pmVO);
	
	void pmDel(final String memberId);
}
