package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizAcceptBO;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IReferInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.ReferInformationVO;

@Service("bizAcceptBO")
public class BizAcceptBOImpl implements IBizAcceptBO {

	@Autowired
	private IInformationDAO informatinDAO;
	
	@Autowired
	private ILifeInformationDAO lifeInformationDAO;
	
	@Autowired
	private IAffairInformationDAO affairInformationDAO;

	@Autowired
	private IReferInformationDAO referInformationDAO;
	
	@Autowired
	private IBaseDAO<PowerInformationVO> piDAO;
	
	
	@Override
	@Transactional
	public void acceptLife(List<InformationVO> list) {
		for(InformationVO vo : list) {
			vo.setStatus(Constants.SYS_INFOMATION_STATES_DB);
			informatinDAO.saveOrUpate(vo);
			LifeInformationVO lifeInfoVO = new LifeInformationVO();
			lifeInfoVO.setInfoId(vo.getInfoId());
			lifeInformationDAO.saveOrUpdate(lifeInfoVO);
			//if help mode is sms, update hj_recv table with status = "C"
			if(Constants.HELP_MODE_SMS.equals(vo.getHelpMode())) {
				
			}
		}
	}
	@Override
	@Transactional
	public void acceptAffair(InformationVO vo) {
		vo.setStatus(Constants.SYS_INFOMATION_STATES_DB);
		informatinDAO.saveOrUpate(vo);
		AffairInformationVO affairInfoVO = new AffairInformationVO();
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
	@Override
	@Transactional
	public void acceptPower(InformationVO vo, PowerInformationVO piVO) throws Exception{
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		informatinDAO.saveOrUpate(vo);
		
		piVO.setInformationId(vo.getInfoId());
		piDAO.saveOrUpdate(piVO);
	}
}
