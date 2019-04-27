package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IBizLifeBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IEntSrvCountDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IVolunteerSrvCountDAO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeCategoryVO;
import com.ccs.vo.LifeInformationVO;

@Service
public class BizLifeBOImpl implements IBizLifeBO {

	@Autowired
	private IInformationDAO informationDAO;
	
	@Autowired
	private ILifeInformationDAO lifeInformationDAO;
	
	@Autowired
	private IVolunteerSrvCountDAO volunteerSrvCountDAO;
	
	@Autowired
	private IEntSrvCountDAO entSrvCountDAO;
	
	@Autowired
	private IBaseDAO<LifeCategoryVO> lifeCategoryDAO;
	
	@Override
	public List<InformationVO> findByCreatorAndStatus(String userId,
			String status, String helpType, PageInfo pageInfo) {
		return informationDAO.findByCreatorAndStatus(userId, status, helpType, pageInfo);
	}

	@Override
	public InformationVO findInfoByInfoId(String infoId) {
		return informationDAO.findById(infoId);
	}

	@Override
	public Map<String, String> getVltSrvCount(String startDt, String endDt, String creator) {
		return volunteerSrvCountDAO.getVolunteerSrvCount(startDt, endDt, creator);
	}

	@Override
	public Map<String, String> getEntSrvCount(String startDt, String endDt, String creator) {
		return entSrvCountDAO.getEntSrvCount(startDt, endDt, creator);
	}

	@Override
	@Transactional
	public void deliverLife(InformationVO infoVO, LifeInformationVO vo) {
		lifeInformationDAO.saveOrUpdate(vo);
		
		infoVO.setStatus(Constants.SYS_INFOMATION_STATES_CLZ);
		informationDAO.saveOrUpate(infoVO);
	}

	@Override
	@Transactional
	public void del(String infoId, String userId) {
		InformationVO vo = informationDAO.findById(infoId);
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YQX);
		vo.setCanceler(userId);
		vo.setCancelTime(new Date());
		informationDAO.saveOrUpate(vo);
	}

	@Override
	public LifeInformationVO findLifeInfoByInfoId(String infoId) {
		return lifeInformationDAO.findByInfoId(infoId);
	}

	@Override
	public List<LifeCategoryVO> getLifeCategory() throws Exception {
		final String sql = "select * from HJ_LIFECATEGORY t start with t.code='0' connect by (prior t.code) = t.parentcode";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>)lifeCategoryDAO.createSQLQuery(sql, new Object[] {}, new Type[]{});
		List<LifeCategoryVO> voList = new ArrayList<LifeCategoryVO>();
		for (Object[] objs : list) {
			LifeCategoryVO vo = new LifeCategoryVO();
			if(!"1".equals(String.valueOf(objs[6]))) {
				vo.setPid(String.valueOf(objs[0]));
				vo.setName(String.valueOf(objs[1]));
				vo.setCode(String.valueOf(objs[2]));
				vo.setParentCode(String.valueOf(objs[3]));
				voList.add(vo);
			}
		}
		return voList;
	}
}
