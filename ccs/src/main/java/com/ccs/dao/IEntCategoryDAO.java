package com.ccs.dao;

import java.util.List;
import java.util.Map;

import com.ccs.vo.EntCategoryVO;

public interface IEntCategoryDAO {
	void saveOrUpdate(EntCategoryVO vo);

	void delete(EntCategoryVO vo);

	EntCategoryVO findById(String categoryId);

	List<EntCategoryVO> findByParentId(String parentId);
	
	Map<String, List<EntCategoryVO>> findAll();
}
