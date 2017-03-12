package com.ccs.report.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.report.vo.InfoDateCountVO;

@Repository("infoDateCountDAO")
@Transactional
public class InfoDateCountDAOImpl extends BaseDAOImpl<InfoDateCountVO> {

}
