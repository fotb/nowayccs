package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.UpInfoStatusVO;

@Repository("upInfoStatusDAO")
@Transactional
public class UpInfoStatusDAOImpl extends BaseDAOImpl<UpInfoStatusVO> {

}
