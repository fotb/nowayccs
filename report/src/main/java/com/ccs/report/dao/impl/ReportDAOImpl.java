package com.ccs.report.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.report.vo.BaseEntity;

@Repository("reportDAO")
@Transactional
public class ReportDAOImpl extends BaseDAOImpl<BaseEntity> {

}
