package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.PowerStaffVO;

@Repository("lpsDAO")
@Transactional
public class LPSDAOImpl extends BaseDAOImpl<PowerStaffVO> {

}
