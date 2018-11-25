package com.ccs.dao;

import java.util.List;

import com.ccs.vo.AffairInformationVO;

public interface IAffairInformationDAO {

	void saveOrUpdate(final AffairInformationVO vo);
	
	void delete(final AffairInformationVO vo);
	
	AffairInformationVO findById(final String affairInfoId);
	
	AffairInformationVO findByInfoId(final String infoId);
	
	List<AffairInformationVO> findByInfoIds(List<String> infoIds);
	
}
