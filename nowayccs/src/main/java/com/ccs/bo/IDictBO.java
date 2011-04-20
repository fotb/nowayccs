package com.ccs.bo;

import java.util.List;

import com.ccs.vo.DictVO;

public interface IDictBO {
	void saveOrUpdate(DictVO vo);

	void delete(DictVO vo);

	DictVO findById(String dictId);

	List<DictVO> findAll();

	List<DictVO> findByType(String dictType);
}
