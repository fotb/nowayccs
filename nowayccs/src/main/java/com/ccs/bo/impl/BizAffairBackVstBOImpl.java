package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizAffairBackVstBO;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;

@Service("bizAffairBackVstBO")
public class BizAffairBackVstBOImpl implements IBizAffairBackVstBO {
	@Autowired
	private IInformationDAO informationDAO;
	@Autowired
	private IAffairInformationDAO affairInformationDAO;
	
	@Override
	public List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo) {
		return informationDAO.findByDeliverer(deliverer, helpType, pageInfo);
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
	public void saveAffairInfo(AffairInformationVO affairInfoVO) {
		affairInformationDAO.saveOrUpdate(affairInfoVO);
	}
	@Override
	@Transactional
	public void bizAffairFinish(AffairInformationVO affairInfoVO, InformationVO infoVO) {
		affairInformationDAO.saveOrUpdate(affairInfoVO);
		informationDAO.saveOrUpate(infoVO);
	}

}
