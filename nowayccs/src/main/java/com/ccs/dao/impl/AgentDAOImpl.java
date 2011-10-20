package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IAgentDAO;
import com.ccs.vo.AgentVO;

@Repository("agentDAO")
public class AgentDAOImpl extends DefaultDAOSupport implements IAgentDAO {

	@Override
	public void saveOrUpdate(AgentVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(AgentVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public AgentVO findById(String userId) {
		return getHibernateTemplate().get(AgentVO.class, userId);
	}
}
