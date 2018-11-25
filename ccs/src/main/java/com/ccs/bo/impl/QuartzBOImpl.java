package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IQuartzBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.QuartzJobVO;

@Service("quartzBO")
@Transactional
public class QuartzBOImpl implements IQuartzBO {

	@Autowired
	private IBaseDAO<QuartzJobVO> quartzJobDAO;
	
	@Override
	public List<QuartzJobVO> findAll() throws Exception {
		return quartzJobDAO.getAll();
	}

	@Override
	public QuartzJobVO findById(String pid) throws Exception {
		return quartzJobDAO.get(pid);
	}

	@Override
	public void add(QuartzJobVO vo) throws Exception {
		quartzJobDAO.save(vo);
	}

	@Override
	public void update(QuartzJobVO vo) throws Exception {
		quartzJobDAO.update(vo);
	}

	@Override
	public void del(String pid) throws Exception {
		QuartzJobVO vo = quartzJobDAO.get(pid);
		quartzJobDAO.delete(vo);
	}

}
