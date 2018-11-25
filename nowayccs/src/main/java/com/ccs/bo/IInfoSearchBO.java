package com.ccs.bo;

import java.util.List;
import java.util.Map;

import com.ccs.bean.InfoSearchBean;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.AppInfoVO;
import com.ccs.vo.ElevHelpInfoVO;
import com.ccs.vo.ElevatorVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.ReferInformationVO;

public interface IInfoSearchBO {

	List<InformationVO> findByParams(InfoSearchBean bean, PageInfo pageInfo);

	Map<String, String> getResult(List<InformationVO> list);
	
	InformationVO findInfoByInfoId(String infoId);
	
	LifeInformationVO findLifeInfoByInfoId(String infoId);
	
	AffairInformationVO findAffairInfoByInfoId(String infoId);
	
	ReferInformationVO findReferInfoByInfoId(String infoId);
	
	AppInfoVO findAppInfoByInfoId(String infoId);
	
	EventVO findSgptInfoByInfoId(String infoId);
	
	ElevHelpInfoVO findElevHelpInfoByInfoId(String infoId);
	
	ElevatorVO getElevator(String pid);
	
	
}
