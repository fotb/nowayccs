package com.ccs.bo;

import java.util.List;
import java.util.Map;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;

public interface IBizLifeBO {

	List<InformationVO> findByCreatorAndStatus(String userId, String status, String helpType, PageInfo pageInfo);
	
	InformationVO findInfoByInfoId(String infoId);

	Map<String, String> getVltSrvCount();
	
	Map<String, String> getVltSrvCountToday();
	
	Map<String, String> getEntSrvCount();
	
	Map<String, String> getEntSrvCountToday();
	
	void deliverLife(InformationVO infoVO, LifeInformationVO vo);
	
	void del(String infoId, String userId);
	
	LifeInformationVO findLifeInfoByInfoId(String infoId);
}
