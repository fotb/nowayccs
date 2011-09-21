package com.ccs.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IEntSrvCountDAO;
import com.ccs.vo.EntSrvCountTodayVO;
import com.ccs.vo.EntSrvCountVO;

@Repository("entSrvCountDAO")
public class EntSrvCountDAOImpl extends DefaultDAOSupport implements
		IEntSrvCountDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getEntSrvCount() {
		List<EntSrvCountVO> list = getHibernateTemplate().find("from EntSrvCountVO t");
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<EntSrvCountVO> iter = list.iterator(); iter.hasNext();) {
			EntSrvCountVO vo = iter.next();
			map.put(vo.getEntpriseId(), String.valueOf(vo.getCount()));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getEntSrvCountToday() {
		List<EntSrvCountTodayVO> list = getHibernateTemplate().find("from EntSrvCountTodayVO t");
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<EntSrvCountTodayVO> iter = list.iterator(); iter.hasNext();) {
			EntSrvCountTodayVO vo = iter.next();
			map.put(vo.getEntpriseId(), String.valueOf(vo.getCount()));
		}
		return map;
	}

}
