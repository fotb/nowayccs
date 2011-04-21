package com.ccs.bo;

import java.util.List;

import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;

public interface IAreaBO {
	void saveOrUpdate(AreaVO vo);

	void saveOrUpdate(AreaSubVO vo);

	void delete(AreaVO vo);

	void delete(AreaSubVO vo);
	
	AreaVO findByAreaId(String areaId);
	
	AreaSubVO findByAreaSubId(String areaSubId);
	
	List<AreaVO> findAllArea();
	
	List<AreaSubVO> findAllAreaSub();
	
	List<AreaSubVO> findAreaSubByAreaId(String areaId);
}
