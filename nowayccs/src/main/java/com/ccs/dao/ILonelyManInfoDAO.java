package com.ccs.dao;

import java.util.List;

import com.ccs.vo.LonelyManInfoVO;

public interface ILonelyManInfoDAO {
	void saveOrUpdate(final LonelyManInfoVO vo);
	
	void delete(final LonelyManInfoVO vo);
	
	LonelyManInfoVO findById(final String manId);
	
	List<LonelyManInfoVO> findByTelphone(final String telphone);
	
	List<String> findLonelyManIds(final String lonelyManName, final String telphone);
}
