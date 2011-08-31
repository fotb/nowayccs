package com.ccs.dao;

import java.util.List;

import com.ccs.vo.OperationVO;

public interface IOperationDAO {
	OperationVO findById(final String operationId);

	void saveOrUpdate(final OperationVO vo);

	void delete(final OperationVO vo);

	List<OperationVO> findAll();
}
