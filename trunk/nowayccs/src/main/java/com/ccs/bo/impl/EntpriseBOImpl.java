package com.ccs.bo.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IEntpriseBO;
import com.ccs.dao.IEntCategoryDAO;
import com.ccs.dao.IEntpriseDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.EntpriseVO;

@Service("entpriseBO")
public class EntpriseBOImpl implements IEntpriseBO {

	@Autowired
	private IEntpriseDAO entpriseDAO;
	@Autowired
	private IEntCategoryDAO entCategoryDAO;

	@Override
	public List<EntpriseVO> findAllEntprise(PageInfo pageInfo) {
		pageInfo.setTotalRecords(entpriseDAO.getTotalCount());
		return entpriseDAO.findAll(pageInfo);
	}

	@Override
	public void addEntprise(EntpriseVO vo) {
		entpriseDAO.saveOrUpdate(vo);
	}

	@Override
	public List<EntCategoryVO> findEntCategoryByParentId(String parentId) {
		return entCategoryDAO.findByParentId(parentId);
	}

	@Override
	public List<EntpriseVO> findByParams(final String entpriseName,
			final String entpriseNo, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, PageInfo pageInfo) {

		pageInfo.setTotalRecords(entpriseDAO.getTotalCount(entpriseName,
				entpriseNo, bigEntclassId, smallEntclassId, entclassId, status));
		return entpriseDAO.findByParams(entpriseName, entpriseNo,
				bigEntclassId, smallEntclassId, entclassId, status, pageInfo);
	}

	@Override
	public Map<String, List<EntCategoryVO>> findAllCategory() {
		return entCategoryDAO.findAll();
	}
}
