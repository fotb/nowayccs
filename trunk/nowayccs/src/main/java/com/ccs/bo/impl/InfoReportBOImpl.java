package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bean.AffairInfoSearchBean;
import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.LifeInfoSearchBean;
import com.ccs.bo.IInfoReportBO;
import com.ccs.dao.IAffairInformationDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IReferInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.ReferInformationVO;

@Service("infoReportBO")
public class InfoReportBOImpl implements IInfoReportBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Autowired
	private IAffairInformationDAO affairInformationDAO;
	
	
	@Autowired
	private ILifeInformationDAO lifeInformationDAO;
	
	@Autowired
	private IReferInformationDAO referInformationDAO;
	
	
	@Override
	public List<InformationVO> findByParams(InfoSearchBean bean, PageInfo pageInfo) {
		pageInfo.setTotalRecords(informationDAO.getTotalCountByParams(bean));
		return informationDAO.findByParams(bean, pageInfo);
	}

	private Map<String, String> findLifeResultByInfoIds(List<String> infoIds) {
		List<LifeInformationVO> list = lifeInformationDAO.findByInfoIds(infoIds);
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<LifeInformationVO> iter = list.iterator(); iter.hasNext();) {
			 LifeInformationVO vo =  iter.next();
			map.put(vo.getInfoId(), vo.getHelpApprove());
		}
		return map;
	}

	private Map<String, String> findAffairResultByInfoIds(
			List<String> infoIds) {
		List<AffairInformationVO> list = affairInformationDAO.findByInfoIds(infoIds);
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<AffairInformationVO> iter = list.iterator(); iter.hasNext();) {
			AffairInformationVO vo =  iter.next();
			map.put(vo.getInfoId(), vo.getHelpApprove());
		}
		return map;
	}

	@Override
	public Map<String, String> getResult(List<InformationVO> list) {
		List<String> lifeInfoIds = new ArrayList<String>();
		List<String> affairInfoIds = new ArrayList<String>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			if (Constants.INFOMATION_HELPTYPE_LIFE.equals(infoVO.getHelpType())) {
				lifeInfoIds.add(infoVO.getInfoId());
			} else if (Constants.INFOMATION_HELPTYPE_AFFAIR.equals(infoVO.getHelpType())) {
				affairInfoIds.add(infoVO.getInfoId());
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.putAll(findLifeResultByInfoIds(lifeInfoIds));
		map.putAll(findAffairResultByInfoIds(affairInfoIds));
		return map;
	}

	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}

	@Override
	public LifeInformationVO findLifeInfoByInfoId(String infoId) {
		return lifeInformationDAO.findByInfoId(infoId);
	}

	@Override
	public AffairInformationVO findAffairInfoByInfoId(String infoId) {
		return affairInformationDAO.findByInfoId(infoId);
	}

	@Override
	public ReferInformationVO findReferInfoByInfoId(String infoId) {
		return referInformationDAO.findByInfoId(infoId);
	}

	
	@Override
	public int getCountByParams(InfoSearchBean bean) {
		return informationDAO.getTotalCountByParams(bean);
	}

	@Override
	public int getInfoCount(String helpType) {
		return informationDAO.getInfoCount(helpType);
	}

	@Override
	public List<InformationVO> findLifeInfoByParams(LifeInfoSearchBean bean,
			PageInfo pageInfo) {
		pageInfo.setTotalRecords(informationDAO.getLifeCountByParams(bean));
		return informationDAO.findLifeInfoByParams(bean, pageInfo);
	}

	@Override
	public Map<String, LifeInformationVO> findLifeInfoByInfoIds(List<String> infoIds) {
		final List<LifeInformationVO> list = lifeInformationDAO.findByInfoIds(infoIds);
		Map<String, LifeInformationVO> map = new HashMap<String, LifeInformationVO>();
		for (Iterator<LifeInformationVO> iter = list.iterator(); iter.hasNext();) {
			LifeInformationVO lifeInfoVO = iter.next();
			map.put(lifeInfoVO.getInfoId(), lifeInfoVO);
		}
		return map;
	}

	@Override
	public int getLifeCount(LifeInfoSearchBean bean) {
		return informationDAO.getLifeCountByParams(bean);
	}

	@Override
	public List<InformationVO> findAffairInfoByParams(
			AffairInfoSearchBean bean, PageInfo pageInfo) {
		return informationDAO.findAffairInfoByParams(bean, pageInfo);
	}

	@Override
	public Map<String, AffairInformationVO> findAffairInfoByInfoIds(
			List<String> infoIds) {
		final List<AffairInformationVO> list = affairInformationDAO.findByInfoIds(infoIds);
		Map<String, AffairInformationVO> map = new HashMap<String, AffairInformationVO>();
		for (Iterator<AffairInformationVO> iter = list.iterator(); iter.hasNext();) {
			AffairInformationVO affairInfoVO = iter.next();
			map.put(affairInfoVO.getInfoId(), affairInfoVO);
		}
		return map;
	}

	@Override
	public int getAffairCount(AffairInfoSearchBean bean) {
		return informationDAO.getAffairCountByParams(bean);
	}
}
