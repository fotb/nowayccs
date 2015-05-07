package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.web.domain.ShsForm;
import com.ccs.web.domain.ShsResultDTO;

public interface ILonelyFamilyBO {
	LonelyManInfoVO findLonelyManInfo(final String telPhone);
	List<PartyMemberForLonelyVO> findByManId(final String manId);
	void helpSave(LonelyHelpVO vo);
	
	List<ShsResultDTO> findSpecialHelp(ShsForm shsForm, PageInfo pageInfo); 
	
	int countSpecialHelp(ShsForm shsForm);
}
