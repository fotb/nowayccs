package com.ccs.bo.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IDictBO;
import com.ccs.dao.IDictDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.DictVO;

@Service("dictBO")
@Transactional
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

	@Override
	public List<DictVO> findByType(String dictType, PageInfo pageInfo) {
		pageInfo.setTotalRecords(dictDAO.getTotalCountByDictType(dictType));
		return dictDAO.findByType(dictType, pageInfo);
	}

	@Override
	public Map<String, String> getDict(String dictType) {
		Map<String, String> map = new HashMap<String, String>();
		List<DictVO> dictList = dictDAO.findByType(dictType);
		for (Iterator<DictVO> iter = dictList.iterator(); iter.hasNext();) {
			DictVO dictVO = iter.next();
			map.put(dictVO.getSortIndex(), dictVO.getValue());
		}
		return map;
	}

}
