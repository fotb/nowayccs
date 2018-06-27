package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizLifeBackVstBO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;

@Service("bizLifeBackVstBO")
public class BizLifeBackVstBOImpl implements IBizLifeBackVstBO {
	@Autowired
	private IInformationDAO informationDAO;
	@Autowired
	private ILifeInformationDAO lifeInformationDAO;
	
	@Override
	public List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo) {
		pageInfo.setTotalRecords(informationDAO.getTotalCountByDeliverer(deliverer, helpType));
		return informationDAO.findByDeliverer(deliverer, helpType, pageInfo);
	}
	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}
	@Override
	public LifeInformationVO findLifeInfoByInfoId(String infoId) {
		return lifeInformationDAO.findByInfoId(infoId);
	}
	@Override
	@Transactional
	public void saveLifeInfo(LifeInformationVO lifeInfoVO) {
		lifeInformationDAO.saveOrUpdate(lifeInfoVO);
	}
	@Override
	@Transactional
	public void bizLifeFinish(LifeInformationVO lifeInfoVO, InformationVO infoVO) {
		lifeInformationDAO.saveOrUpdate(lifeInfoVO);
		informationDAO.saveOrUpate(infoVO);
	}

}
