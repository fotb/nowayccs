package com.ccs.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IInformationBO;
import com.ccs.dao.IInformationDAO;
import com.ccs.vo.InformationVO;

@Service("informationBO")
public class InformationBOImpl implements IInformationBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	
	@Override
	public InformationVO findById(String infoId) {
		return informationDAO.findById(infoId);
	}

}
