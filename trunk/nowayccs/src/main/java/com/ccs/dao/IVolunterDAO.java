package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.VolunteerVO;

public interface IVolunterDAO {
	void saveOrUpate(final VolunteerVO vo);
	
	void delete(final VolunteerVO vo);
	
	VolunteerVO findById(final String volunteerId);
	
	List<VolunteerVO> findByParams(String status, String serviceType,
			 String areaId, String areaSubId, String volunteerNo, 
			 PageInfo pageInfo);
	
	int getCountByParams(String status, String serviceType,
			 String areaId, String areaSubId, String volunteerNo);
}
