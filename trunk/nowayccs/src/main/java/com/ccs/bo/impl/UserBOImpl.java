package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IUserBO;
import com.ccs.dao.IUserDAO;
import com.ccs.dao.IUserRoleDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.UserRoleIdVO;
import com.ccs.vo.UserRoleVO;
import com.ccs.vo.UserVO;

@Service("userBO")
public class UserBOImpl implements IUserBO {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IUserRoleDAO userRoleDAO;
	
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
	public void addUser(UserVO vo, String[] roleIds) {
		userDAO.saveOrUpdate(vo);
		
		List<UserRoleVO> list = new ArrayList<UserRoleVO>();
		for (int i = 0; i < roleIds.length; i++) {
			UserRoleVO userRoleVO = new UserRoleVO();
			UserRoleIdVO idVO = new UserRoleIdVO();
			idVO.setRoleId(roleIds[i]);
			idVO.setUserId(vo.getUserId());
			userRoleVO.setId(idVO);
			list.add(userRoleVO);
		}
		userRoleDAO.saveOrUpdate(list);
	}

    @Override
    @Transactional
	public void editUser(UserVO vo, String[] roleIds) {
		userDAO.update(vo);
		
		List<UserRoleVO> oldUserRoleVOList = userRoleDAO.findByUserId(vo.getUserId());
		userRoleDAO.delete(oldUserRoleVOList);
		
		List<UserRoleVO> list = new ArrayList<UserRoleVO>();
		for (int i = 0; i < roleIds.length; i++) {
			UserRoleVO userRoleVO = new UserRoleVO();
			UserRoleIdVO idVO = new UserRoleIdVO();
			idVO.setRoleId(roleIds[i]);
			idVO.setUserId(vo.getUserId());
			userRoleVO.setId(idVO);
			list.add(userRoleVO);
			userRoleDAO.merge(userRoleVO);
		}
	}

    
	@Override
	public List<UserVO> findByLoginName(String loginName) {
		return userDAO.findByLoginName(loginName);
	}

	@Override
	public List<UserVO> findAllOnJob() {
		return userDAO.findAllOnJob();
	}

	@Override
	public List<UserRoleVO> findUserRoleByUserId(String userId) {
		return userRoleDAO.findByUserId(userId);
	}

}
