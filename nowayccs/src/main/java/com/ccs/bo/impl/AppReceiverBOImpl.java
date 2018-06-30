package com.ccs.bo.impl;

import java.util.Date;

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
		vo.setCreateTime(new Date());
		appReceiverDAO.save(vo);
	}

}
