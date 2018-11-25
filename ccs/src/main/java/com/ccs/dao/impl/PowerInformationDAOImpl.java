package com.ccs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ccs.util.DateUtil;
import com.ccs.vo.PowerInformationVO;

@Repository("piDAO")
@Transactional
public class PowerInformationDAOImpl extends BaseDAOImpl<PowerInformationVO> {

	public List<?> queryInfoList(String startDt, String endDt, int page, int rows) {

		String sql = "select t.informationid, t.helpname, t.helptel, t.helpaddr, t.helpcontent, "
				+ "t.creator, t.createtime,d.powerstaffid, d.areasubid, s.name as areasubname, "
				+ "a.name as areaname from hj_information t, HJ_POWERINFORMATION d, hj_area_sub s, hj_area a "
				+ "where t.informationId = d.informationid and d.areasubid = s.areasubid and s.areaid = a.areaid "
				+ "and (t.createtime >= ? or ? is null) and (t.createtime <= ? or ? is null)";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setDate(0, DateUtil.parse(startDt, "yyyy-MM-dd"));
		query.setDate(1, DateUtil.parse(startDt, "yyyy-MM-dd"));
		query.setDate(2, DateUtil.parse(endDt, "yyyy-MM-dd"));
		query.setDate(3, DateUtil.parse(endDt, "yyyy-MM-dd"));
		
//		query.setParameters(arg0, arg1)
		
		query.setFirstResult((page - 1) * rows).setMaxResults(rows);
		
		return query.list();

	}
}
