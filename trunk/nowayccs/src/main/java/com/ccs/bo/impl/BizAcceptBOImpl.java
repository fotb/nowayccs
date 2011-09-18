package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizAcceptBO;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.IReferInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.ReferInformationVO;

@Service("bizAcceptBO")
public class BizAcceptBOImpl implements IBizAcceptBO {

	@Autowired
	private IInformationDAO informatinDAO;
	@Autowired
	private IAffairInformationDAO affairInformationDAO;
	@Autowired
	private IReferInformationDAO referInformationDAO;
	
	@Override
	public void acceptLife(InformationVO vo) {
		vo.setStatus(Constants.SYS_INFOMATION_STATES_DB);
		informatinDAO.saveOrUpate(vo);
	}
	@Override
	@Transactional
	public void acceptAffair(InformationVO vo, AffairInformationVO affairInfoVO) {
		vo.setStatus(Constants.SYS_INFOMATION_STATES_DB);
		informatinDAO.saveOrUpate(vo);
		
		affairInfoVO.setInfoId(vo.getInfoId());
		affairInformationDAO.saveOrUpdate(affairInfoVO);
	}
	
	@Override
	@Transactional
	public void acceptRefer(InformationVO vo, ReferInformationVO referInfoVO) {
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		informatinDAO.saveOrUpate(vo);
		
		referInfoVO.setInfoId(vo.getInfoId());
		referInformationDAO.saveOrUpdate(referInfoVO);		
	}
	@Override
	public List<InformationVO> findByInfoByTel(String helpTel, PageInfo pageInfo) {
		pageInfo.setTotalRecords(informatinDAO.getTotalCount(helpTel));
		return informatinDAO.findByHelpTel(helpTel, pageInfo);
	}

}
