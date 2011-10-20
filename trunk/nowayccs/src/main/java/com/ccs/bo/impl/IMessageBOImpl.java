package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IMessageBO;
import com.ccs.dao.IMessageDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.MessageVO;

@Service("messageBO")
public class IMessageBOImpl implements IMessageBO {

	@Autowired
	private IMessageDAO messageDAO;
	
	@Override
	public void saveOrUpdate(MessageVO vo) {
		messageDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(MessageVO vo) {
		messageDAO.delete(vo);
	}

	@Override
	public MessageVO findById(String msgId) {
		return messageDAO.findById(msgId);
	}

	@Override
	public List<MessageVO> findByParams(String msgType, String creator,
			String title, String startDt, String endDt, PageInfo pageInfo) {
		pageInfo.setTotalRecords(messageDAO.getCountByParams(msgType, creator, title, startDt, endDt));
		return messageDAO.findByParams(msgType, creator, title, startDt, endDt, pageInfo);
	}
}
