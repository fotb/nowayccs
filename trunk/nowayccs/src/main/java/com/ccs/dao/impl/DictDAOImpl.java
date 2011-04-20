package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IDictDAO;
import com.ccs.vo.DictVO;

@Repository("dictDAO")
public class DictDAOImpl extends DefaultDAOSupport implements IDictDAO {

	@Override
	public DictVO findById(String dictId) {
		return (DictVO) getHibernateTemplate().get(DictVO.class, dictId);
	}

	@Override
	public void saveOrUpdate(DictVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(DictVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DictVO> findByType(String dictType) {
		return getHibernateTemplate().find(
				"from DictVO vo where vo.dictType = ? order by vo.sortIndex",
				dictType);
	}

}
