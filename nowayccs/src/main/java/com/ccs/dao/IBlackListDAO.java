package com.ccs.dao;

import java.util.List;

import com.ccs.vo.BlackListVO;

public interface IBlackListDAO {

	void saveOrUpdate(BlackListVO vo);
	
	void delete(BlackListVO vo);
	
	BlackListVO findById(String phoneId);
	
	BlackListVO findByPhoneNum(String phoneNum); 
	
	List<BlackListVO> findByParams(String phoneNum, String levels);
}
