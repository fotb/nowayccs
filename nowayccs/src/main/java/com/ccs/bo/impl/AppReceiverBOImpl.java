package com.ccs.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.AppReceiverVO;
@Service("appReceiverBO")
public class AppReceiverBOImpl implements IAppReceiverBO {

	@Autowired
	private IBaseDAO<AppReceiverVO> appReceiverDAO;
	
	
	@Override
	public void create(AppReceiverVO vo) throws Exception {
		appReceiverDAO.save(vo);
	}

}
