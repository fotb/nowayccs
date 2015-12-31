package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.ReferInformationVO;

public interface IBizAcceptBO {
	void acceptLife(List<InformationVO> list);
	
	void acceptAffair(InformationVO vo);
	
	void acceptRefer(InformationVO vo, ReferInformationVO referInfoVO);
	
	List<InformationVO> findByInfoByTel(String helpTel, PageInfo pageInfo);
	
	void acceptPower(InformationVO vo, PowerInformationVO piVO) throws Exception;
	
}