package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.vo.AppReceiverVO;


@Repository("appReceiverDAO")
@Transactional
public class AppReceiverDAOImpl extends BaseDAOImpl<AppReceiverVO> {

}
