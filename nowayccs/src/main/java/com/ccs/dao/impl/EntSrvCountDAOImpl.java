package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IEntSrvCountDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.StringUtil;
import com.ccs.vo.EntSrvCountVO;

@Repository("entSrvCountDAO")
public class EntSrvCountDAOImpl extends DefaultDAOSupport implements
		IEntSrvCountDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getEntSrvCount(String startDt, String endDt, String creator) {
		StringBuffer buffer = new StringBuffer(1000);
		List<Object> objs = new ArrayList<Object>();
		
		buffer.append("select new com.ccs.vo.EntSrvCountVO(e.entpriseId, count(t.lifeInfoId) as count) ");
		buffer.append("from InformationVO i, LifeInformationVO t, EntpriseVO e ");
		buffer.append("where i.infoId = t.infoId and t.receiverId = e.entpriseId "); 
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
		buffer.append("and t.receiverType = ? and e.status = ? and e.servicesType = ? ");
		buffer.append("group by e.entpriseId");
		objs.add("2");
		objs.add("Y");
		objs.add("Y");
		
		List<EntSrvCountVO> list = getHibernateTemplate().find(buffer.toString(), objs.toArray());
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<EntSrvCountVO> iter = list.iterator(); iter.hasNext();) {
			EntSrvCountVO vo = iter.next();
			map.put(vo.getEntpriseId(), String.valueOf(vo.getCount()));
		}
		return map;
	}
}
