package com.ccs.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IAgentBO;
import com.ccs.dao.IAgentDAO;
import com.ccs.vo.AgentVO;

@Service("agentBO")
public class AgentBOImpl implements IAgentBO {

	@Autowired
	private IAgentDAO agentDAO;
	
	@Override
	public void saveOrUpdate(AgentVO vo) {
		agentDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(AgentVO vo) {
		agentDAO.delete(vo);
	}

	@Override
	public AgentVO findById(String userId) {
		return agentDAO.findById(userId);
	}

}
