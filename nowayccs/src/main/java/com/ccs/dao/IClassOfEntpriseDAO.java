package com.ccs.dao;

import java.util.List;

import com.ccs.vo.ClassOfEntpriseIdVO;
import com.ccs.vo.ClassOfEntpriseVO;

public interface IClassOfEntpriseDAO {
	
	ClassOfEntpriseVO findById(ClassOfEntpriseIdVO idVO);
	
	void saveOrUpdate(ClassOfEntpriseVO vo);
	
	void saveOrUpdate(List<ClassOfEntpriseVO> list);
	
	void delete(ClassOfEntpriseVO vo);
	
	void merge(ClassOfEntpriseVO vo);
	
	List<ClassOfEntpriseVO> findByEntpriseId(String entpriseId);
	
}
