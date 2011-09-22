package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

public interface IInformationDAO {

	void saveOrUpate(final InformationVO vo);
	
	void delete(final InformationVO vo);
	
	InformationVO findById(final String infoId);
	
	List<InformationVO> findByHelpTel(final String helpTel, PageInfo pageInfo);
	
	int getTotalCount(final String helpTel);
	
	List<InformationVO> findByCreatorAndStatus(final String userId, final String status, final String helpType, PageInfo pageInfo);
	
	int getTotalCount(final String userId, final String status, String helpType);
	
	List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo);
	
	int getTotalCountByDeliverer(String deliverer, String helpType);
}
