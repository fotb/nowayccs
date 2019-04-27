package com.ccs.bo;

import java.util.List;

import com.ccs.vo.LifeCategoryVO;

public interface ILifeCategoryBO {

	LifeCategoryVO findById(String pid);
	
	void update(LifeCategoryVO vo)  throws Exception;
	
	void delete(LifeCategoryVO vo) throws Exception;
	

	void save(LifeCategoryVO vo) throws Exception;
	
	List<LifeCategoryVO> findByCode(String code);
	
}
