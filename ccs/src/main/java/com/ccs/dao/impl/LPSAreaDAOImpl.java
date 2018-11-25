package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.PowerStaffAreaVO;

@Repository("lpsaDAO")
@Transactional
public class LPSAreaDAOImpl extends BaseDAOImpl<PowerStaffAreaVO> {

}
