package com.ccs.bo;

import com.ccs.vo.AgentVO;

public interface IAgentBO {
	void saveOrUpdate(AgentVO vo);

	void delete(AgentVO vo);

	AgentVO findById(String userId);
}
