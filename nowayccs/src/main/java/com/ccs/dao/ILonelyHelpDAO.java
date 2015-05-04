package com.ccs.dao;

import com.ccs.vo.LonelyHelpVO;

public interface ILonelyHelpDAO {

	void saveOrUpdate(final LonelyHelpVO vo);
	
	void delete(final LonelyHelpVO vo);
	
	LonelyHelpVO findById(final String helpId);
}
