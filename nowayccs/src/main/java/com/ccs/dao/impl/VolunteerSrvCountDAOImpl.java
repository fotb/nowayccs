package com.ccs.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IVolunteerSrvCountDAO;
import com.ccs.vo.VolunteerSrvCountTodayVO;
import com.ccs.vo.VolunteerSrvCountVO;

@Repository("volunteerSrvCountDAO")
public class VolunteerSrvCountDAOImpl extends DefaultDAOSupport implements
		IVolunteerSrvCountDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getVolunteerSrvCount() {
		List<VolunteerSrvCountVO> list = getHibernateTemplate().find("from VolunteerSrvCountVO t");
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<VolunteerSrvCountVO> iter = list.iterator(); iter.hasNext();) {
			VolunteerSrvCountVO vo = iter.next();
			map.put(vo.getVolunteerId(), String.valueOf(vo.getCount()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getVolunteerSrvCountToday() {
		List<VolunteerSrvCountTodayVO> list = getHibernateTemplate().find("from VolunteerSrvCountTodayVO t");
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<VolunteerSrvCountTodayVO> iter = list.iterator(); iter.hasNext();) {
			VolunteerSrvCountTodayVO vo = iter.next();
			map.put(vo.getVolunteerId(), String.valueOf(vo.getCount()));
		}
		return map;
	}

}
