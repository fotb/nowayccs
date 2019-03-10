package com.ccs.bo;

import java.util.List;
import java.util.Map;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeCategoryVO;
import com.ccs.vo.LifeInformationVO;

public interface IBizLifeBO {

	List<InformationVO> findByCreatorAndStatus(String userId, String status, String helpType, PageInfo pageInfo);
	
	InformationVO findInfoByInfoId(String infoId);

	Map<String, String> getVltSrvCount(String startDt, String endDt, String creator);
	
	Map<String, String> getEntSrvCount(String startDt, String endDt, String creator);
	
	void deliverLife(InformationVO infoVO, LifeInformationVO vo);
	
	void del(String infoId, String userId);
	
	LifeInformationVO findLifeInfoByInfoId(String infoId);
	
	public List<LifeCategoryVO> getLifeCategory() throws Exception;
}
