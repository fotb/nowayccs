package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IXzspListBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.EasyUiTree;
import com.ccs.util.XzspTreeUtil;
import com.ccs.vo.UserVO;
import com.ccs.vo.XzspIndexVO;
import com.ccs.vo.XzspListVO;
import com.ccs.web.domain.XzspListDomain;

@Service("xzspListBO")
public class XzspListBOImpl implements IXzspListBO {
	@Autowired
	private IBaseDAO<XzspListVO> xzspListDAO;
	
	@Autowired
	private IBaseDAO<XzspIndexVO> xzspIndexDAO;

	@Override
	public void save(XzspListDomain domain, UserVO user) throws Exception {
			
		XzspListVO vo = new XzspListVO();
		vo.setListCode(domain.getListCode());
		vo.setItemName(domain.getItemName());
		vo.setCode(domain.getCode());
		vo.setItemType(domain.getItemType());
		vo.setTarget(domain.getTarget());
		vo.setAccording(domain.getAccording());
		vo.setRequirement(domain.getRequirement());
		vo.setMaterials(domain.getMaterials());
		vo.setProce(domain.getProce());
		vo.setDealDept(domain.getDealDept());
		vo.setLegalTerm(domain.getLegalTerm());
		vo.setPromiseDate(domain.getPromiseDate());
		vo.setChargeStand(domain.getChargeStand());
		vo.setChargeAccording(domain.getChargeAccording());
		vo.setDealPhone(domain.getDealPhone());
		vo.setServicePhone(domain.getServicePhone());
		vo.setEasyLevel(domain.getEasyLevel());
		
		Date today = DateUtil.getToday();
		vo.setCreateTime(today);
		vo.setUpdateDT(today);
		vo.setLastHandler(user.getUserId());
		vo.setDeleteFlag(XzspListVO.DELETE_FLAG_NO);
		xzspListDAO.save(vo);
	}

	@Override
	public List<XzspListVO> findAll() throws Exception {
		return xzspListDAO.getAll("listCode");
	}

	@Override
	public XzspListVO findById(String pid) throws Exception {
		return xzspListDAO.get(pid);
	}

	@Override
	public void editSave(XzspListDomain domain, UserVO userVO) throws Exception {
		XzspListVO vo = xzspListDAO.get(domain.getPid());
		
		vo.setListCode(domain.getListCode());
		vo.setItemName(domain.getItemName());
		vo.setCode(domain.getCode());
		vo.setItemType(domain.getItemType());
		vo.setTarget(domain.getTarget());
		vo.setAccording(domain.getAccording());
		vo.setRequirement(domain.getRequirement());
		vo.setMaterials(domain.getMaterials());
		vo.setProce(domain.getProce());
		vo.setDealDept(domain.getDealDept());
		vo.setLegalTerm(domain.getLegalTerm());
		vo.setPromiseDate(domain.getPromiseDate());
		vo.setChargeStand(domain.getChargeStand());
		vo.setChargeAccording(domain.getChargeAccording());
		vo.setDealPhone(domain.getDealPhone());
		vo.setServicePhone(domain.getServicePhone());
		vo.setEasyLevel(domain.getEasyLevel());
		
		Date today = DateUtil.getToday();
		vo.setCreateTime(today);
		vo.setUpdateDT(today);
		vo.setLastHandler(userVO.getUserId());
		vo.setDeleteFlag(XzspListVO.DELETE_FLAG_NO);
		xzspListDAO.update(vo);
	}

	@Override
	public void del(String pid) throws Exception {
		XzspListVO vo = xzspListDAO.get(pid);
		xzspListDAO.delete(vo);
	}

	@Override
	public EasyUiTree buildListCodeTree() throws Exception {
		List<XzspIndexVO> voList = xzspIndexDAO.getAll();
		XzspTreeUtil util = new XzspTreeUtil(voList);
		
		return util.generateEasyUiTree("0");
	}

	@Override
	public List<XzspListVO> search(String key) throws Exception {
		List<XzspListVO> list = xzspListDAO.queryForObject("from XzspListVO t where (t.listCode like ? or ? is null) or (t.itemName like ? or ? is null) order by pid", new String[]{"%" + key + "%", key, "%" + key + "%", key,});
		return list;
	}

	@Override
	public void save(XzspListVO vo) throws Exception {
		xzspListDAO.save(vo);
	}

	@Override
	public List<XzspListVO> findByListCode(String code) throws Exception {
		List<XzspListVO> list = xzspListDAO.queryForObject("from XzspListVO t where t.listCode = ? order by pid", new String[]{code});
		return list;
	}

	@Override
	public List<XzspListVO> findAll(int page, int rows) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria condition = DetachedCriteria.forClass(XzspListVO.class);
		return xzspListDAO.findByDetachedCriteria(condition, page, rows);
	}	
	
	@Override
	public int getAllCount() throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria condition = DetachedCriteria.forClass(XzspListVO.class);
		return xzspListDAO.getRowCountByDetachedCriteria(condition);
	}	
}
