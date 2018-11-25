package com.ccs.bo;

import java.util.List;

import com.ccs.vo.QuartzJobVO;

public interface IQuartzBO {
	List<QuartzJobVO> findAll() throws Exception;
	
	QuartzJobVO findById(String pid) throws Exception;
	
	void add(QuartzJobVO vo) throws Exception;
	
	void update(QuartzJobVO vo) throws Exception;
	
	void del(String pid) throws Exception;
}
