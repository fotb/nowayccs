package com.ccs.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.bo.IAreaBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.dao.IVolunteerDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.VolunteerVO;

@Service("volunteerBO")
public class VolunteerBOImpl implements IVolunteerBO {

	@Autowired
	private IVolunteerDAO volunteerDAO;
	
	@Autowired
	private IAreaBO areaBO;

	@Override
	public void saveOrUpdate(VolunteerVO vo) {
		volunteerDAO.saveOrUpate(vo);
	}

	@Override
	public VolunteerVO findById(String volunteerId) {
		return volunteerDAO.findById(volunteerId);
	}

	@Override
	public List<VolunteerVO> search(String status, String serviceType,
			String areaId, String areaSubId, String volunteerNo, String serviceName, 
			PageInfo pageInfo) {
		pageInfo.setTotalRecords(volunteerDAO.getCountByParams(status,
				serviceType, areaId, areaSubId, volunteerNo, serviceName));
		return volunteerDAO.findByParams(status, serviceType, areaId,
				areaSubId, volunteerNo, serviceName, pageInfo);
	}

	@Override
	public List<AreaVO> getAllArea() {
		return areaBO.findAllArea();
	}

	@Override
	public List<AreaSubVO> getSubAreaByAreaId(String areaId) {
		return areaBO.findAreaSubByAreaId(areaId);
	}

}
