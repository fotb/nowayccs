package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;

public interface IBizLifeBackVstBO {

	List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo);
	
	InformationVO findInfoByInfoId(String infoId);
	
	LifeInformationVO findLifeInfoByInfoId(String infoId);
	
	void saveLifeInfo(LifeInformationVO lifeInfoVO);
	
	void bizLifeFinish(LifeInformationVO lifeInfoVO, InformationVO infoVO);
}
