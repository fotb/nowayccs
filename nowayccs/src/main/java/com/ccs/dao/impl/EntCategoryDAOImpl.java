package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IEntCategoryDAO;
import com.ccs.vo.EntCategoryVO;

@Repository("entCategoryDAO")
public class EntCategoryDAOImpl extends DefaultDAOSupport implements
		IEntCategoryDAO {

	@Override
	public void saveOrUpdate(EntCategoryVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(EntCategoryVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public EntCategoryVO findById(String categoryId) {
		return (EntCategoryVO)getHibernateTemplate().get(EntCategoryVO.class, categoryId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntCategoryVO> findByParentId(String parentId) {
		return getHibernateTemplate().find("from EntCategoryVO vo where vo.parentId = ?", parentId);
	}

}
