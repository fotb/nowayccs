package com.ccs.dao;

import com.ccs.vo.AgentVO;

public interface IAgentDAO {

	void saveOrUpdate(AgentVO vo);
	
	void delete(AgentVO vo);
	
	AgentVO findById(String userId);
}
