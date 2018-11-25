package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IVolunteerSrvCountDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.StringUtil;
import com.ccs.vo.VolunteerSrvCountVO;

@Repository("volunteerSrvCountDAO")
public class VolunteerSrvCountDAOImpl extends DefaultDAOSupport implements
		IVolunteerSrvCountDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getVolunteerSrvCount(String startDt, String endDt, String creator) {
		StringBuffer buffer = new StringBuffer(1000);
		List<Object> objs = new ArrayList<Object>();
		
		buffer.append("select new com.ccs.vo.VolunteerSrvCountVO(v.volunteerId, count(t.lifeInfoId) as count) ");
		buffer.append("from InformationVO i, LifeInformationVO t, VolunteerVO v ");
		buffer.append("where i.infoId = t.infoId and t.receiverId = v.volunteerId "); 
		if(!StringUtil.isNull(startDt)) {
			buffer.append("and trunc(i.deliverTime) >= ? ");
			objs.add(DateUtil.parse(startDt, "yyyy-MM-dd"));
		}
		if(!StringUtil.isNull(endDt)) {
			buffer.append("and trunc(i.deliverTime) <= ? ");
			objs.add(DateUtil.parse(endDt, "yyyy-MM-dd"));
		}
		if(!StringUtil.isNull(creator)) {
			buffer.append("and i.creator = ? ");
			objs.add(creator);
		}
		buffer.append("and v.serviceType = ? and t.receiverType = ? ");
		buffer.append("group by v.volunteerId");
		
		objs.add("Y");
		objs.add("1");
		List<VolunteerSrvCountVO> list = (List<VolunteerSrvCountVO>) getHibernateTemplate().find(buffer.toString(), objs.toArray());
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<VolunteerSrvCountVO> iter = list.iterator(); iter.hasNext();) {
			VolunteerSrvCountVO vo = iter.next();
			map.put(vo.getVolunteerId(), String.valueOf(vo.getCount()));
		}
		return map;
	}

}
