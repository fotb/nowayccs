package com.ccs.report.bo;

import java.util.Date;
import java.util.List;

import com.ccs.util.AgentStatusBean;
import com.ccs.util.CountHelpTypeBean;
import com.ccs.util.InfoAreaCountBean;
import com.ccs.util.YearCountBean;

public interface IReportBO {
	
	List<YearCountBean> queryInfoCountByMonth(String startDt, String endDt) throws Exception;
	
	List<CountHelpTypeBean> countInfoByHelpType(String startDt, String endDt) throws Exception;
	
	List<AgentStatusBean> queryAgentStatus() throws Exception;

	List<AgentStatusBean> countPhone(Date today) throws Exception;

	List<AgentStatusBean> countPhone(Date today, String targetDevice) throws Exception;
	
	int queryInTimeCallCount() throws Exception;
	
	List<InfoAreaCountBean> countInfoByArea() throws Exception;
}
