package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.PowerInformationVO;

@Repository("piDAO")
@Transactional
public class PowerInformationDAOImpl extends BaseDAOImpl<PowerInformationVO> {

}
