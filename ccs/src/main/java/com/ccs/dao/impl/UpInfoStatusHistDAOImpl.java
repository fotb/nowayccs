package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.UpInfoStatusHistVO;

@Repository("upInfoStatusHistDAO")
@Transactional
public class UpInfoStatusHistDAOImpl extends BaseDAOImpl<UpInfoStatusHistVO> {

}
