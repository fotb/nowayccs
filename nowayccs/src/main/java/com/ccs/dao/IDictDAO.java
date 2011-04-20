package com.ccs.dao;

import java.util.List;

import com.ccs.vo.DictVO;

public interface IDictDAO {
	public DictVO findById(String dictId);
	
	void saveOrUpdate(DictVO vo);
	
	void delete(DictVO vo);
	
	List<DictVO> findByType(String dictType);
	
	
}
