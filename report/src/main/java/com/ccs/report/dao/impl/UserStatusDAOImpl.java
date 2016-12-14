package com.ccs.report.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.report.vo.UserStatusVO;

@Repository("userStatusDAO")
@Transactional
public class UserStatusDAOImpl extends BaseDAOImpl<UserStatusVO> {

}
