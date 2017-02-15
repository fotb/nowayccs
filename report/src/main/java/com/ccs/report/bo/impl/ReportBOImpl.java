package com.ccs.report.bo.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.ccs.util.AgentStatusBean;
import com.ccs.util.CountHelpTypeBean;
import com.ccs.util.DateUtil;
import com.ccs.util.YearCountBean;

@Service("reportBO")
public class ReportBOImpl implements IReportBO {

	@Autowired
	private IBaseDAO<BaseEntity> reportDAO;

	@Override
	public List<YearCountBean> queryInfoCountByMonth(String startDt, String endDt) throws Exception {
		final String sql = "select to_char(t.createtime, 'YYYY-MM') month, count(t.informationId) from ccs.hj_information t "
				+ "where to_char(t.createtime, 'YYYYMM') > ? and to_char(t.createtime, 'YYYYMM') <= ?  "
				+ "group by to_char(t.createtime, 'YYYY-MM') " + "order by to_char(t.createtime, 'YYYY-MM')";

		List<?> list = reportDAO.createSQLQuery(sql, new String[] { startDt, endDt },
				new Type[] { StandardBasicTypes.STRING, StandardBasicTypes.STRING });

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

		List<?> list = reportDAO.createSQLQuery(sql, new String[] { startDt, endDt },
				new Type[] { StandardBasicTypes.STRING, StandardBasicTypes.STRING });

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

	@Override
	public List<AgentStatusBean> queryAgentStatus() throws Exception {
		
		final String sql = "select trim(t.agentid), u.userid, u.username, a.targetdevice, trim(t.status), s.phoneno "
				+ "from cti_agentstatus t, hj_agent a,hj_user u, hj_userstatus s "
				+ "where a.workno = trim(t.agentid) and u.userid = a.userid and u.userid = s.userid and u.onjob='1' and trim(t.status) != '0'";
		
		List<?> list = reportDAO.createSQLQuery(sql);
		List<AgentStatusBean> beanList = new ArrayList<AgentStatusBean>();
		for (Object obj : list) {
			AgentStatusBean bean = new AgentStatusBean();
			Object[] arrs = (Object[]) obj;
			bean.setAgentId(String.valueOf(arrs[0]));
			bean.setUserId(String.valueOf(arrs[1]));
			bean.setUserName(String.valueOf(arrs[2]));
			bean.setTargetDevice(String.valueOf(arrs[3]));
			bean.setStatus(String.valueOf(arrs[4]));
			bean.setPhoneNo(String.valueOf(arrs[5]));
			beanList.add(bean);
		}
		return beanList;
	}

	@Override
	public List<AgentStatusBean> countPhone(Date today) throws Exception {
		final String dSql = "select t.creator, count(t.informationId) from hJ_information t  "
				+ "where to_char(t.createtime,'yyyymmdd') = ? group by t.creator";
		final String mSql = "select t.creator, count(t.informationId) from hJ_information t  "
				+ "where to_char(t.createtime,'yyyymm') >= ? and to_char(t.createtime,'yyyymm') < ?  group by t.creator";
		final String ySql = "select t.creator, count(t.informationId) from hJ_information t  "
				+ "where to_char(t.createtime,'yyyy') >= ? and to_char(t.createtime,'yyyy') < ? group by t.creator";
		
		final String sql = "select a.userid,c.username, a.targetdevice, b.status, b.agentstatus, b.phoneno "
				+ "from hj_agent a left join hj_userstatus b on a.userid=b.userid "
				+ "left join hj_user c on a.userid=c.userid "
				+ "where c.onjob='1' and b.status = '1' order by a.targetdevice";
		List<?> list = reportDAO.createSQLQuery(sql);
		Map<String, AgentStatusBean> beanMap = new HashMap<String, AgentStatusBean>();
		for (Object obj : list) {
			AgentStatusBean bean = new AgentStatusBean();
			Object[] arrs = (Object[]) obj;
			bean.setUserId(String.valueOf(arrs[0]));
			bean.setUserName(String.valueOf(arrs[1]));
			bean.setTargetDevice(String.valueOf(arrs[2]));
			bean.setStatus(String.valueOf(arrs[3]));
			bean.setAgentStatus(String.valueOf(arrs[4]));
			bean.setPhoneNo(String.valueOf(arrs[5]));
			beanMap.put(bean.getUserId(), bean);
		}
		
		List<?> dList = reportDAO.createSQLQuery(dSql, new String[]{DateUtil.format(today, "yyyyMMdd")}, new Type[]{StandardBasicTypes.STRING});
		for (Object obj : dList) {
			Object[] arrs = (Object[]) obj;
			if(beanMap.containsKey(String.valueOf(arrs[0]))) {
				AgentStatusBean bean = beanMap.get(String.valueOf(arrs[0]));
				bean.setDayCount(Integer.valueOf(String.valueOf(arrs[1])));
			}
		}
		
		Date nextMonth = DateUtil.addMonth(today, 1);
		List<?> mList = reportDAO.createSQLQuery(mSql, new String[]{DateUtil.format(today, "yyyyMM"), DateUtil.format(nextMonth, "yyyyMM")}, new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING});
		for (Object obj : mList) {
			Object[] arrs = (Object[]) obj;
			if(beanMap.containsKey(String.valueOf(arrs[0]))) {
				AgentStatusBean bean = beanMap.get(String.valueOf(arrs[0]));
				bean.setMonthCount(Integer.valueOf(String.valueOf(arrs[1])));
			}
		}
		
		Date nextYear = DateUtil.addYear(today, 1);
		List<?> yList = reportDAO.createSQLQuery(ySql, new String[]{DateUtil.format(today, "yyyy"), DateUtil.format(nextYear, "yyyy")}, new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING});
		for (Object obj : yList) {
			Object[] arrs = (Object[]) obj;
			if(beanMap.containsKey(String.valueOf(arrs[0]))) {
				AgentStatusBean bean = beanMap.get(String.valueOf(arrs[0]));
				bean.setYearCount(Integer.valueOf(String.valueOf(arrs[1])));
			}
		}
		return new ArrayList<AgentStatusBean>(beanMap.values());
	}

}
