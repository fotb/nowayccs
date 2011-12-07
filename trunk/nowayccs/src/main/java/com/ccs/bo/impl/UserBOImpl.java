package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bean.TrafficSearchBean;
import com.ccs.bean.UserTrafficBean;
import com.ccs.bo.IUserBO;
import com.ccs.dao.IOperationDAO;
import com.ccs.dao.IRoleOperationDAO;
import com.ccs.dao.IUserDAO;
import com.ccs.dao.IUserRoleDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.OperationVO;
import com.ccs.vo.RoleOperationVO;
import com.ccs.vo.UserRoleIdVO;
import com.ccs.vo.UserRoleVO;
import com.ccs.vo.UserVO;

@Service("userBO")
public class UserBOImpl implements IUserBO {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IUserRoleDAO userRoleDAO;
	
	@Autowired
	private IRoleOperationDAO roleOperationDAO;
	
	@Autowired
	private IOperationDAO operationDAO;
	
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
	public UserVO login(String loginName, String pwd) {
		return userDAO.findByLoginNameAndPwd(loginName, pwd);
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

	@Override
	public List<UserVO> findUserByOpertaionId(String operationId) {
		List<RoleOperationVO> roleOprVOList = roleOperationDAO.findByOperationId(operationId);
		List<String> roleIdList = new ArrayList<String>();
		for (Iterator<RoleOperationVO> iter = roleOprVOList.iterator(); iter.hasNext();) {
			RoleOperationVO roleOprVO = iter.next();
			roleIdList.add(roleOprVO.getId().getRoleId());
		}
		
		List<String> userIdList = userRoleDAO.findUserIdsByRoleIds(roleIdList);
		return userDAO.findByUserIds(userIdList);
	}

	@Override
	public boolean hasOperationRight(String userId, String operationId) {
		List<RoleOperationVO> roleOprVOList = roleOperationDAO.findByOperationId(operationId);
		List<String> roleIdList = new ArrayList<String>();
		for (Iterator<RoleOperationVO> iter = roleOprVOList.iterator(); iter.hasNext();) {
			RoleOperationVO roleOprVO = iter.next();
			roleIdList.add(roleOprVO.getId().getRoleId());
		}
		
		List<String> userIdList = userRoleDAO.findUserIdsByRoleIds(roleIdList);
		boolean result = false;
		if(userIdList.contains(userId)) {
			result = true;
		}
		return result;
	}

	@Override
	public Map<String, UserVO> findAll() {
		List<UserVO> list = userDAO.findAll();
		Map<String, UserVO> map = new HashMap<String, UserVO>();
		for (Iterator<UserVO> iter = list.iterator(); iter.hasNext();) {
			UserVO userVO = iter.next();
			map.put(userVO.getUserId(), userVO);
		}
		return map;
	}

	@Override
	public List<UserTrafficBean> findUserTraffic(TrafficSearchBean bean) {
		return userDAO.findUserTraffic(bean);
	}

	@Override
	public Map<String, OperationVO> findUserOpertaionRightByUserId(String userId) {
		List<OperationVO> list = operationDAO.findByUserId(userId);
		Map<String, OperationVO> map = new HashMap<String, OperationVO>();
		for(OperationVO vo : list) {
//			map.put(vo.getClassName() + vo.getAction(), vo);
			map.put(vo.getClassName(), vo);
		}
		return map;
	}

	@Override
	public Map<String, UserVO> findOnJob() {
		List<UserVO> list =  userDAO.findAllOnJob();
		Map<String, UserVO> map = new HashMap<String, UserVO>();
		for (UserVO userVO : list) {
			map.put(userVO.getUserId(), userVO);
		}
		return map;
	}
}
