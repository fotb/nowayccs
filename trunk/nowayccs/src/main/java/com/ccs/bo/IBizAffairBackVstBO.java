package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;

public interface IBizAffairBackVstBO {

	List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo);
	
	InformationVO findInfoByInfoId(String infoId);
	
	AffairInformationVO findAffairInfoByInfoId(String infoId);
	
	void saveAffairInfo(AffairInformationVO affairInfoVO);
	
	void bizAffairFinish(AffairInformationVO affairInfoVO, InformationVO infoVO);
}
