package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.ReferVO;

public interface IReferBO {
	void saveoOrUpdate(ReferVO vo);
	
	void delete(ReferVO vo);
	
	ReferVO findById(String referId);
	
	List<ReferVO> findByParams(String title, String referType, PageInfo pageInfo);
}
