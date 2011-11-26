package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		return getHibernateTemplate().find("from EntCategoryVO vo where vo.parentId = ? order by vo.categoryId", parentId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<EntCategoryVO>> findAll() {
		List<EntCategoryVO> list = getHibernateTemplate().find("from EntCategoryVO vo");
		Map<String, List<EntCategoryVO>> map = new HashMap<String, List<EntCategoryVO>>();
		for (Iterator<EntCategoryVO> iter = list.iterator(); iter.hasNext();) {
			EntCategoryVO ecVO = (EntCategoryVO) iter.next();
			if(map.containsKey(ecVO.getParentId())) {
				List<EntCategoryVO> tempList = map.get(ecVO.getParentId());
				tempList.add(ecVO);
			} else {
				List<EntCategoryVO> tempList = new ArrayList<EntCategoryVO>();
				tempList.add(ecVO);
				map.put(ecVO.getParentId(), tempList);
			}
		}
		return map;
	}


}
