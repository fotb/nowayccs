package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.ReferVO;

public interface IReferDAO {

	void saveoOrUpdate(ReferVO vo);
	
	void delete(ReferVO vo);
	
	ReferVO findById(String referId);
	
	List<ReferVO> findByParams(String title, String referType, PageInfo pageInfo);
	
	int getCountByParams(String title, String referType);
}
