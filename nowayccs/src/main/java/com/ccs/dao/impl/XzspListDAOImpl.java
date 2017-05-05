package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.XzspListVO;

@Repository("xzspListDAO")
@Transactional
public class XzspListDAOImpl extends BaseDAOImpl<XzspListVO> {

}
