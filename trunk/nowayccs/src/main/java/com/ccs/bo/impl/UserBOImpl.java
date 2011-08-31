package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IUserBO;
import com.ccs.dao.IUserDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.UserVO;

@Service("userBO")
public class UserBOImpl implements IUserBO {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public void saveOrUpdate(UserVO vo) {
		userDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(UserVO vo) {
		userDAO.delete(vo);
	}

	@Override
	public UserVO findById(String userId) {
		return userDAO.findById(userId);
	}

	@Override
	public List<UserVO> findAll(PageInfo pageInfo) {
		pageInfo.setTotalRecords(userDAO.getTotalCount());
		return userDAO.findAll(pageInfo);
	}

	@Override
	public boolean login(String loginName, String pwd) {
		final UserVO vo = userDAO.findByLoginNameAndPwd(loginName, pwd);
		return null == vo ? false : true;
	}

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addUser(UserVO vo) {
		userDAO.saveOrUpdate(vo);
		//TODO
		//add user role
	}

	@Override
	public List<UserVO> findByLoginName(String loginName) {
		return userDAO.findByLoginName(loginName);
	}

	@Override
	public List<UserVO> findAllOnJob() {
		return userDAO.findAllOnJob();
	}

}
