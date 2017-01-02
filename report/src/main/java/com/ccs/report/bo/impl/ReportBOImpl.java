package com.ccs.report.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.report.bo.IReportBO;
import com.ccs.report.dao.IBaseDAO;
import com.ccs.report.vo.BaseEntity;
import com.ccs.util.CountHelpTypeBean;
import com.ccs.util.YearCountBean;

import net.sf.json.JSONArray;

@Service("reportBO")
public class ReportBOImpl implements IReportBO {

	@Autowired
	private IBaseDAO<BaseEntity> reportDAO;
	
	@Override
	public List<YearCountBean> queryInfoCountByMonth(String startDt, String endDt) throws Exception {
		final String sql = "select to_char(t.createtime, 'YYYY-MM') month, count(t.informationId) from ccs.hj_information t "
				+ "where to_char(t.createtime, 'YYYYMM') > ? and to_char(t.createtime, 'YYYYMM') <= ?  "
				+ "group by to_char(t.createtime, 'YYYY-MM') "
				+ "order by to_char(t.createtime, 'YYYY-MM')";
		
		List<?> list = reportDAO.createSQLQuery(sql, new String[]{startDt, endDt}, new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING});

		List<YearCountBean> beanList = new ArrayList<YearCountBean>();
		for (Object obj : list) {
			YearCountBean bean = new YearCountBean();
			Object[] arrs = (Object[]) obj;
			bean.setDate(String.valueOf(arrs[0]));
			bean.setCount(Integer.valueOf(String.valueOf(arrs[1])));
			beanList.add(bean);
		}
		return beanList;
	}

	@Override
	public List<CountHelpTypeBean> countInfoByHelpType(String startDt, String endDt) throws Exception {
		final String sql = "select t.helptype, count(t.informationId) from ccs.hj_information t "
				+ "where to_char(t.createtime, 'YYYYMM') >= ? and to_char(t.createtime, 'YYYYMM') <= ?  "
				+ "group by t.helptype";
		
		List<?> list = reportDAO.createSQLQuery(sql, new String[]{startDt, endDt}, new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING});

		List<CountHelpTypeBean> beanList = new ArrayList<CountHelpTypeBean>();
		for (Object obj : list) {
			CountHelpTypeBean bean = new CountHelpTypeBean();
			Object[] arrs = (Object[]) obj;
			bean.setHelpType(String.valueOf(arrs[0]));
			bean.setCount(Integer.valueOf(String.valueOf(arrs[1])));
			beanList.add(bean);
		}
		return beanList;
	}

}
