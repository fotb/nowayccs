package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IBlackListBO;
import com.ccs.dao.IBlackListDAO;
import com.ccs.vo.BlackListVO;

@Service("blackListBO")
public class BlackListBOImpl implements IBlackListBO {

	@Autowired
	private IBlackListDAO blackListDAO;
	
	@Override
	public void saveOrUpdate(BlackListVO vo) {
		blackListDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(BlackListVO vo) {
		blackListDAO.delete(vo);
	}

	@Override
	public BlackListVO findById(String phoneId) {
		return blackListDAO.findById(phoneId);
	}

	@Override
	public BlackListVO findByPhoneNum(String phoneNum) {
		return blackListDAO.findByPhoneNum(phoneNum);
	}

	@Override
	public List<BlackListVO> findByParams(String phoneNum, String levels) {
		return blackListDAO.findByParams(phoneNum, levels);
	}

}
