package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IEntpriseBO;
import com.ccs.dao.IClassOfEntpriseDAO;
import com.ccs.dao.IEntCategoryDAO;
import com.ccs.dao.IEntpriseDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.ClassOfEntpriseIdVO;
import com.ccs.vo.ClassOfEntpriseVO;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.EntpriseVO;

@Service("entpriseBO")
public class EntpriseBOImpl implements IEntpriseBO {

	@Autowired
	private IEntpriseDAO entpriseDAO;
	@Autowired
	private IEntCategoryDAO entCategoryDAO;
	
	@Autowired
	private IClassOfEntpriseDAO classOfEntpriseDAO;

	@Override
	public List<EntpriseVO> findAllEntprise(PageInfo pageInfo) {
		pageInfo.setTotalRecords(entpriseDAO.getTotalCount());
		return entpriseDAO.findAll(pageInfo);
	}

	@Override
	public void addEntprise(EntpriseVO vo) {
		vo.setStatus(Constants.SYS_YESNO_YES);
		entpriseDAO.saveOrUpdate(vo);
	}

	@Override
	public void updateEntprise(EntpriseVO vo) {
		entpriseDAO.saveOrUpdate(vo);
	}
	
	@Override
	public List<EntCategoryVO> findEntCategoryByParentId(String parentId) {
		return entCategoryDAO.findByParentId(parentId);
	}

	@Override
	public List<EntpriseVO> findByParams(final String entpriseName,
			final String entpriseNo, final String servicesType, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, final String address, PageInfo pageInfo) {

		pageInfo.setTotalRecords(entpriseDAO.getTotalCount(entpriseName,
				entpriseNo, servicesType, bigEntclassId, smallEntclassId, entclassId, status, address));
		return entpriseDAO.findByParams(entpriseName, entpriseNo, servicesType, 
				bigEntclassId, smallEntclassId, entclassId, status, address, pageInfo);
	}

	@Override
	public Map<String, List<EntCategoryVO>> findAllCategory() {
		return entCategoryDAO.findAll();
	}

	@Override
	public EntpriseVO findEntByEntpriseId(String entpriseId) {
		return entpriseDAO.findById(entpriseId);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addCategoryToEntprise(String entpriseId, List<String> categoryIdList) {
		List<ClassOfEntpriseVO> oldCategoryList = classOfEntpriseDAO.findByEntpriseId(entpriseId);
//		if(!oldCategoryList.isEmpty()) {
//			classOfEntpriseDAO.deleteAll(oldCategoryList);
//		}
		
		List<ClassOfEntpriseVO> coeVOList = new ArrayList<ClassOfEntpriseVO>();
		for (Iterator<String> iter = categoryIdList.iterator(); iter.hasNext();) {
			String categoryId = (String) iter.next();
			ClassOfEntpriseVO coeVO = new ClassOfEntpriseVO();
			ClassOfEntpriseIdVO coeIdVO = new ClassOfEntpriseIdVO();
			coeIdVO.setCategory(categoryId);
			coeIdVO.setEntpriseId(entpriseId);
			coeVO.setId(coeIdVO);
			coeVOList.add(coeVO);
		}
		List<ClassOfEntpriseVO> removeCoeVOList = new ArrayList<ClassOfEntpriseVO>();
		List<ClassOfEntpriseVO> newCoeVOList = new ArrayList<ClassOfEntpriseVO>();
		for (ClassOfEntpriseVO coeVO : coeVOList) {
			if(!oldCategoryList.contains(coeVO)) {
				newCoeVOList.add(coeVO);
			}
		}
		
		for (ClassOfEntpriseVO coeVO : oldCategoryList) {
			if(!coeVOList.contains(coeVO)) {
				removeCoeVOList.add(coeVO);
			}
		}
		
		if(!removeCoeVOList.isEmpty()) {
			classOfEntpriseDAO.deleteAll(removeCoeVOList);
		}
		
		if(!newCoeVOList.isEmpty()) {
			classOfEntpriseDAO.saveOrUpdate(newCoeVOList);
		}
		
//		if(!coeVOList.isEmpty()) {
//			classOfEntpriseDAO.saveOrUpdate(coeVOList);
//		}
	}

	@Override
	public List<ClassOfEntpriseVO> findCOEByEntpriseId(String entpriseId) {
		return classOfEntpriseDAO.findByEntpriseId(entpriseId);
	}

	@Override
	public Map<String, EntpriseVO> findAll2Map() {
		List<EntpriseVO> list = entpriseDAO.findAll();
		Map<String, EntpriseVO> map = new HashMap<String, EntpriseVO>();
		for (Iterator<EntpriseVO> iter = list.iterator(); iter.hasNext();) {
			EntpriseVO entpriseVO = iter.next();
			map.put(entpriseVO.getEntpriseId(), entpriseVO);
		}
		return map;
	}
}
