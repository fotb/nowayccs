package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizAffairBO;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;

@Service("bizAffairBO")
public class BizAffairBOImpl implements IBizAffairBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Autowired
	private IAffairInformationDAO affairInformationDAO;

	@Override
	public List<InformationVO> findInfoByParams(String creator, String status,
			String helpType, PageInfo pageInfo) {
		pageInfo.setTotalRecords(informationDAO.getTotalCount(creator, status,
				helpType));
		return informationDAO.findByCreatorAndStatus(creator, status, helpType,
				pageInfo);
	}

	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}

	@Override
	public AffairInformationVO findAffairInfoByInfoId(String infoId) {
		return affairInformationDAO.findByInfoId(infoId);
	}

	@Override
	@Transactional
	public void deliverAffair(InformationVO infoVO, AffairInformationVO vo) {
		infoVO.setStatus(Constants.SYS_INFOMATION_STATES_CLZ);
		informationDAO.saveOrUpate(infoVO);
		
		affairInformationDAO.saveOrUpdate(vo);
	}

	
	@Override
	public void del(String infoId, String userId) {
		InformationVO vo = informationDAO.findById(infoId);
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YQX);
		vo.setCanceler(userId);
		vo.setCancelTime(new Date());
		informationDAO.saveOrUpate(vo);
	}
}
