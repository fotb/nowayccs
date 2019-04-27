package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.ILifeCategoryBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.LifeCategoryVO;

@Service("lifeCategoryBO")
public class LifeCategoryBOImpl implements ILifeCategoryBO {

	@Autowired
	private IBaseDAO<LifeCategoryVO> lifeCategoryDAO;
	
	@Override
	public LifeCategoryVO findById(String pid) {
		return lifeCategoryDAO.get(pid);
	}

	@Override
	public void update(LifeCategoryVO vo) throws Exception {
		lifeCategoryDAO.update(vo);
	}

	@Override
	public void delete(LifeCategoryVO vo) throws Exception {
		vo.setDeleteFlag(LifeCategoryVO.DELETE_FLAG_YES);
		lifeCategoryDAO.update(vo);
	}

	@Override
	public void save(LifeCategoryVO vo) throws Exception {
		lifeCategoryDAO.save(vo);
	}

	@Override
	public List<LifeCategoryVO> findByCode(String code) {
		return lifeCategoryDAO.queryForObject("from LifeCategoryVO where code = ?", new Object[] {code});
	}
}
