package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.ReferInformationVO;

public interface IBizAcceptBO {
	void acceptLife(InformationVO vo);
	
	void acceptAffair(InformationVO vo, AffairInformationVO affairInfoVO);
	
	void acceptRefer(InformationVO vo, ReferInformationVO referInfoVO);
	
	List<InformationVO> findByInfoByTel(String helpTel, PageInfo pageInfo);
}