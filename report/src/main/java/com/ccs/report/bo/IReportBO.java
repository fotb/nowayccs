package com.ccs.report.bo;

import java.util.Date;
import java.util.List;

import com.ccs.report.util.AgentStatusBean;
import com.ccs.report.util.CountHelpTypeBean;
import com.ccs.report.util.InfoAreaCountBean;
import com.ccs.report.util.InfoDateCountBean;
import com.ccs.report.util.YearCountBean;
import com.ccs.report.vo.HistCountVO;

public interface IReportBO {
	
	List<YearCountBean> queryInfoCountByMonth(String startDt, String endDt) throws Exception;
	
	List<CountHelpTypeBean> countInfoByHelpType(String startDt, String endDt) throws Exception;
	
	List<AgentStatusBean> queryAgentStatus() throws Exception;

	List<AgentStatusBean> countPhone(Date today) throws Exception;

	List<AgentStatusBean> countPhone(Date today, String targetDevice) throws Exception;
	
	int queryInTimeCallCount() throws Exception;
	
	List<InfoAreaCountBean> countInfoByArea() throws Exception;
	
	List<InfoDateCountBean> countByDate(String fromDate) throws Exception;
	
	List<HistCountVO> countHsitByYear() throws Exception;
}
