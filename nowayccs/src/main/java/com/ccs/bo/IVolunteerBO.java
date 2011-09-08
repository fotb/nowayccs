package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.VolunteerVO;

public interface IVolunteerBO {
	void saveOrUpdate(VolunteerVO vo);

	VolunteerVO findById(String volunteerId);

	List<VolunteerVO> search(String status, String serviceType, String areaId,
			String areaSubId, String volunteerNo, PageInfo pageInfo);

	List<AreaVO> getAllArea();
	
	List<AreaSubVO> getSubAreaByAreaId(String areaId);
}
