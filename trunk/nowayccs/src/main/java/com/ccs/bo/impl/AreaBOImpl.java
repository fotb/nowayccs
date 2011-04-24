package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IAreaBO;
import com.ccs.dao.IAreaDAO;
import com.ccs.dao.IAreaSubDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;

@Service("areaBO")
public class AreaBOImpl implements IAreaBO {

	@Autowired
	private IAreaDAO areaDAO;

	@Autowired
	private IAreaSubDAO areaSubDAO;

	@Override
	public void saveOrUpdate(AreaVO vo) {
		areaDAO.saveOrUpdate(vo);
	}

	@Override
	public void saveOrUpdate(AreaSubVO vo) {
		areaSubDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(AreaVO vo) {
		areaDAO.delete(vo);
	}

	@Override
	public void delete(AreaSubVO vo) {
		areaSubDAO.delete(vo);
	}

	@Override
	public AreaVO findByAreaId(String areaId) {
		return areaDAO.findById(areaId);
	}

	@Override
	public AreaSubVO findByAreaSubId(String areaSubId) {
		return areaSubDAO.findById(areaSubId);
	}

	@Override
	public List<AreaVO> findAllArea() {
		return areaDAO.findAll();
	}

	@Override
	public List<AreaSubVO> findAllAreaSub() {
		return areaSubDAO.findAll();
	}

	@Override
	public List<AreaSubVO> findAreaSubByAreaId(String areaId) {
		return areaSubDAO.findByAreaId(areaId);
	}

	@Override
	public List<AreaVO> findAllArea(PageInfo pageInfo) {
		return areaDAO.findAll(pageInfo);
	}

}
