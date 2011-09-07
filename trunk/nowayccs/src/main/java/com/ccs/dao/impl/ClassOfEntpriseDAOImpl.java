package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IClassOfEntpriseDAO;
import com.ccs.vo.ClassOfEntpriseIdVO;
import com.ccs.vo.ClassOfEntpriseVO;

@Repository("classOfEntpriseDAO")
public class ClassOfEntpriseDAOImpl extends DefaultDAOSupport implements
		IClassOfEntpriseDAO {

	@Override
	public ClassOfEntpriseVO findById(ClassOfEntpriseIdVO idVO) {
		return getHibernateTemplate().get(ClassOfEntpriseVO.class, idVO);
	}

	@Override
	public void saveOrUpdate(ClassOfEntpriseVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void saveOrUpdate(List<ClassOfEntpriseVO> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public void delete(ClassOfEntpriseVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public void merge(ClassOfEntpriseVO vo) {
		getHibernateTemplate().merge(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassOfEntpriseVO> findByEntpriseId(String entpriseId) {
		return getHibernateTemplate().find("from ClassOfEntpriseVO vo where vo.id.entpriseId = ?", entpriseId);
	}

	@Override
	public void deleteAll(List<ClassOfEntpriseVO> list) {
		getHibernateTemplate().deleteAll(list);
	}

}
