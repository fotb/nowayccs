package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IPartyMemberForLonelyDAO;
import com.ccs.vo.PartyMemberForLonelyVO;

@Repository("partyMemberForLonelyDAO")
public class PartyMemberForLonelyDAOImpl extends DefaultDAOSupport implements
		IPartyMemberForLonelyDAO {

	@Override
	public void saveOrUpdate(PartyMemberForLonelyVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(PartyMemberForLonelyVO vo) {
		getHibernateTemplate().delete(vo);
	}
	
	@Override
	public PartyMemberForLonelyVO findById(String memberId) {
		return getHibernateTemplate().get(PartyMemberForLonelyVO.class, memberId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PartyMemberForLonelyVO> findByManId(String manId) {
		return getHibernateTemplate().find("from PartyMemberForLonelyVO where lonelyManId = ?", manId);
	}
}
