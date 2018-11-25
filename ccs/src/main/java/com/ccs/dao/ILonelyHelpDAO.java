package com.ccs.dao;

import java.util.Date;
import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.LonelyHelpVO;

public interface ILonelyHelpDAO {

	void saveOrUpdate(final LonelyHelpVO vo);
	
	void delete(final LonelyHelpVO vo);
	
	LonelyHelpVO findById(final String helpId);
	
	List<LonelyHelpVO> query(final List<String> lonelyManIds, final List<String> memberIds, final Date startDt, final Date endDt, final PageInfo pageInfo);
	int queryCount(final List<String> lonelyManIds, final List<String> memberIds, final Date startDt, final Date endDt);
}
