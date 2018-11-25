package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.HelpTypeVO;


@Repository("helpTypeDAO")
@Transactional
public class HelpTypeDAOImpl extends BaseDAOImpl<HelpTypeVO> {

}
