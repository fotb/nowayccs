package com.ccs.dao;

import java.util.List;

import com.ccs.vo.AreaVO;

public interface IAreaDAO {

	void saveOrUpdate(AreaVO vo);

	void delete(AreaVO vo);

	AreaVO findById(String areaId);

	List<AreaVO> findAll();
}
