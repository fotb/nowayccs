package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizAcceptBO;
import com.ccs.bo.IUserAppInfoBO;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IReferInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.AppReceiverHistVO;
import com.ccs.vo.AppReceiverVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.ReferInformationVO;
import com.ccs.vo.UserAppInfoVO;

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
	
	@Autowired
	private IBaseDAO<AppReceiverVO> appReceiverDAO;
	
	@Autowired
	private IBaseDAO<AppReceiverHistVO> appReceiverHistDAO;
	
	@Autowired
	private IBaseDAO<UserAppInfoVO> userAppInfoDAO;
	
	@Autowired
	private IUserAppInfoBO userAppInfoBO;
	
	
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
	public void acceptAppLife(List<InformationVO> list, String appInfoId) throws Exception {
			InformationVO vo = list.get(0); //App求助不支持多求助信息.
			vo.setStatus(Constants.SYS_INFOMATION_STATES_DB);
			informatinDAO.saveOrUpate(vo);
			LifeInformationVO lifeInfoVO = new LifeInformationVO();
			lifeInfoVO.setInfoId(vo.getInfoId());
			lifeInformationDAO.saveOrUpdate(lifeInfoVO);
			
			//if help mode is app, update app_receiver table, delete record
			if(Constants.HELP_MODE_APP.equals(vo.getHelpMode())) {
				AppReceiverVO appReceiverVO = appReceiverDAO.get(appInfoId);
				AppReceiverHistVO appRecieverHistVO = new AppReceiverHistVO(appReceiverVO);
				appRecieverHistVO.setAgentId(vo.getCreator());
				appRecieverHistVO.setCreateTime(DateUtil.getNowDate());
				appReceiverHistDAO.saveOrUpdate(appRecieverHistVO);
				List<UserAppInfoVO> userAppInfoVOList = userAppInfoBO.findByAppInfoId(appInfoId);
				for (UserAppInfoVO userAppInfoVO : userAppInfoVOList) {
					userAppInfoDAO.delete(userAppInfoVO);
				}
				appReceiverDAO.delete(appReceiverVO);
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
	@Transactional
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
