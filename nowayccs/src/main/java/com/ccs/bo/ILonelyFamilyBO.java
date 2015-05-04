package com.ccs.bo;

import java.util.List;

import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;

public interface ILonelyFamilyBO {
	LonelyManInfoVO findLonelyManInfo(final String telPhone);
	List<PartyMemberForLonelyVO> findByManId(final String manId);
}
