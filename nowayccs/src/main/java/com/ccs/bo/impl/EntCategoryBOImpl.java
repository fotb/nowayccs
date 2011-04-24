package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IEntCategoryBO;
import com.ccs.dao.IEntCategoryDAO;
import com.ccs.vo.EntCategoryVO;

@Service("entCategoryBO")
public class EntCategoryBOImpl implements IEntCategoryBO {

	@Autowired
	private IEntCategoryDAO entCategoryDAO;
	
	@Override
	public void saveOrUpdate(EntCategoryVO vo) {
		entCategoryDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(EntCategoryVO vo) {
		entCategoryDAO.delete(vo);
	}

	@Override
	public EntCategoryVO findById(String categoryId) {
		return entCategoryDAO.findById(categoryId);
	}

	@Override
	public List<EntCategoryVO> findByParentId(String parentId) {
		return entCategoryDAO.findByParentId(parentId);
	}

}
