package com.ccs.report.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.report.vo.HistCountVO;

@Repository("histCountDAO")
@Transactional
public class HistCountDAOImpl extends BaseDAOImpl<HistCountVO> {

}
