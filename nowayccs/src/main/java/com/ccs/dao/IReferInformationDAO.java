package com.ccs.dao;

import java.util.List;

import com.ccs.vo.ReferInformationVO;

public interface IReferInformationDAO {

	void saveOrUpdate(final ReferInformationVO vo);
	
	void delete(final ReferInformationVO vo);
	
	ReferInformationVO findById(final String affairInfoId);
	
	ReferInformationVO findByInfoId(final String infoId);
	
	List<ReferInformationVO> findByInfoIds(List<String> infoIds);
	
}
