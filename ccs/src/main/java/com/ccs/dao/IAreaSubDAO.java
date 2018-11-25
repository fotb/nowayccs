package com.ccs.dao;

import java.util.List;

import com.ccs.vo.AreaSubVO;

public interface IAreaSubDAO {
	void saveOrUpdate(AreaSubVO vo);

	void delete(AreaSubVO vo);

	AreaSubVO findById(String areaSubId);

	List<AreaSubVO> findAll();
	
	List<AreaSubVO> findByAreaId(String areaId);
}
