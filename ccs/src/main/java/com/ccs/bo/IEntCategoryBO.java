package com.ccs.bo;

import java.util.List;

import com.ccs.vo.EntCategoryVO;

public interface IEntCategoryBO {
	void saveOrUpdate(EntCategoryVO vo);
	
	void delete(EntCategoryVO vo);
	
	EntCategoryVO findById(String categoryId);
	
	List<EntCategoryVO> findByParentId(String parentId);
}
