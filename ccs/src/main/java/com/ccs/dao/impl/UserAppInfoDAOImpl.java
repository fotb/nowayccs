package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.UserAppInfoVO;

@Repository("userAppInfoDAO")
@Transactional
public class UserAppInfoDAOImpl extends BaseDAOImpl<UserAppInfoVO> {

}
