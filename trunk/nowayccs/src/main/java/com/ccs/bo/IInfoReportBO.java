package com.ccs.bo;

import java.util.List;
import java.util.Map;

import com.ccs.bean.AffairInfoSearchBean;
import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.LifeInfoSearchBean;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.ReferInformationVO;

public interface IInfoReportBO {

	List<InformationVO> findByParams(InfoSearchBean bean, PageInfo pageInfo);

	Map<String, String> getResult(List<InformationVO> list);
	
	InformationVO findInfoByInfoId(String infoId);
	
	LifeInformationVO findLifeInfoByInfoId(String infoId);
	
	AffairInformationVO findAffairInfoByInfoId(String infoId);
	
	ReferInformationVO findReferInfoByInfoId(String infoId);
	
	int getCountByParams(InfoSearchBean bean);
	
	int getInfoCount(String helpType);
	
	List<InformationVO> findLifeInfoByParams(LifeInfoSearchBean bean, PageInfo pageInfo);
	
	Map<String, LifeInformationVO> findLifeInfoByInfoIds(List<String> infoIds);
	
	int getLifeCount(LifeInfoSearchBean bean);
	
	List<InformationVO> findAffairInfoByParams(AffairInfoSearchBean bean, PageInfo pageInfo);
	
	int getAffairCount(AffairInfoSearchBean bean);
	
	Map<String, AffairInformationVO> findAffairInfoByInfoIds(List<String> infoIds);
}
