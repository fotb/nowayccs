package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.web.domain.LfMgrForm;

public interface ILonelyManInfoDAO {
	void saveOrUpdate(final LonelyManInfoVO vo);
	
	void delete(final LonelyManInfoVO vo);
	
	LonelyManInfoVO findById(final String manId);
	
	List<LonelyManInfoVO> findByTelphone(final String telphone);
	
	List<String> findLonelyManIds(final String lonelyManName, final String telphone);
	
	List<LonelyManInfoVO> queryManInfo(final LfMgrForm lfMgrForm, final PageInfo pageInfo);
	
	int countManInfo(final LfMgrForm lfMgrForm);
}
