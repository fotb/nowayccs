package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IBizLifeBackVstBO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

@Service("bizLifeBackVstBO")
public class BizLifeBackVstBOImpl implements IBizLifeBackVstBO {
	@Autowired
	private IInformationDAO informationDAO;
	@Override
	public List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo) {
		return informationDAO.findByDeliverer(deliverer, helpType, pageInfo);
	}

}
