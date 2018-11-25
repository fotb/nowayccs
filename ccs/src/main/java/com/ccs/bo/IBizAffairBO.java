package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;

public interface IBizAffairBO {
	List<InformationVO> findInfoByParams(String creator, String status,
			String helpType, PageInfo pageInfo);
	
	InformationVO findInfoByInfoId(String infoId);
	
	AffairInformationVO findAffairInfoByInfoId(String infoId);
	
	void deliverAffair(InformationVO infoVO, AffairInformationVO vo);
	
	public void del(String infoId, String userId);
}
