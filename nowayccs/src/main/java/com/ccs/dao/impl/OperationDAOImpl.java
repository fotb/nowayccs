package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IOperationDAO;
import com.ccs.vo.OperationVO;

@Repository("operationDAO")
public class OperationDAOImpl extends DefaultDAOSupport implements
		IOperationDAO {

	@Override
	public OperationVO findById(String operationId) {
		return getHibernateTemplate().get(OperationVO.class, operationId);
	}

	@Override
	public void saveOrUpdate(OperationVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(OperationVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OperationVO> findAll() {
		return getHibernateTemplate().find("from OperationVO");
	}

}
