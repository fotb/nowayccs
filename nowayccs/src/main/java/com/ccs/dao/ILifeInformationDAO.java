package com.ccs.dao;

import java.util.List;

import com.ccs.vo.LifeInformationVO;

public interface ILifeInformationDAO {

	void saveOrUpdate(final LifeInformationVO vo);
	
	void delete(final LifeInformationVO vo);
	
	LifeInformationVO findById(final String affairInfoId);
	
	LifeInformationVO findByInfoId(final String infoId);
	
	List<LifeInformationVO> findByInfoIds(List<String> infoIds);
	
}
