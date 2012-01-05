package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.BlackListVO;

public interface IBlackListDAO {

	void saveOrUpdate(BlackListVO vo);
	
	void delete(BlackListVO vo);
	
	BlackListVO findById(String phoneId);
	
	BlackListVO findByPhoneNum(String phoneNum); 
	
	List<BlackListVO> findByParams(String phoneNum, String levels, PageInfo pageInfo);
	
	int getTotalByParams(String phoneNum, String levels);
}
