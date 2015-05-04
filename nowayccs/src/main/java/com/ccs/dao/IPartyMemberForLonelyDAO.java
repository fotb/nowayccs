package com.ccs.dao;

import java.util.List;

import com.ccs.vo.PartyMemberForLonelyVO;

public interface IPartyMemberForLonelyDAO {
	
	void saveOrUpdate(final PartyMemberForLonelyVO vo);
	
	void delete(final PartyMemberForLonelyVO vo);
	
	PartyMemberForLonelyVO findById(final String memberId);
	
	List<PartyMemberForLonelyVO> findByManId(final String manId);
}
