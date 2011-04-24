package com.ccs.dao;

import java.util.List;

import com.ccs.vo.EntCategoryVO;

public interface IEntCategoryDAO {
	void saveOrUpdate(EntCategoryVO vo);

	void delete(EntCategoryVO vo);

	EntCategoryVO findById(String categoryId);

	List<EntCategoryVO> findByParentId(String parentId);

}
