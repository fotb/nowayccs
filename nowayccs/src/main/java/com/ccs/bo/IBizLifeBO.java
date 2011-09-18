package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

public interface IBizLifeBO {

	List<InformationVO> findByCreatorAndStatus(String userId, String status, String helpType, PageInfo pageInfo);
	
	InformationVO findInfoByInfoId(String infoId);

}
