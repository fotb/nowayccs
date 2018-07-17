package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IHelpTypeBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.HelpTypeVO;

@Service("helpTypeBO")
public class HelpTypeBOImpl implements IHelpTypeBO {

	@Autowired
	private IBaseDAO<HelpTypeVO> helpTypeDAO;

	@Override
	public List<HelpTypeVO> getList(String parentId) {
		return helpTypeDAO.queryForObject("from HelpTypeVO where parentId = ?", new Object[] { parentId });
	}
}
