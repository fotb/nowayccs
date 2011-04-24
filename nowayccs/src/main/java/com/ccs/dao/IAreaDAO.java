package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.AreaVO;

public interface IAreaDAO {

	void saveOrUpdate(AreaVO vo);

	void delete(AreaVO vo);

	AreaVO findById(String areaId);

	List<AreaVO> findAll();
	
	List<AreaVO> findAll(PageInfo pageInfo);
}
