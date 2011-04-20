package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccs.bo.IDictBO;
import com.ccs.dao.IDictDAO;
import com.ccs.vo.DictVO;

public class DictBOImpl implements IDictBO {
	
	@Autowired
	private IDictDAO dictDAO;

	@Override
	public void saveOrUpdate(DictVO vo) {
		dictDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(DictVO vo) {
		dictDAO.delete(vo);
	}

	@Override
	public DictVO findById(String dictId) {
		return dictDAO.findById(dictId);
	}

	@Override
	public List<DictVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DictVO> findByType(String dictType) {
		return dictDAO.findByType(dictType);
	}

}
