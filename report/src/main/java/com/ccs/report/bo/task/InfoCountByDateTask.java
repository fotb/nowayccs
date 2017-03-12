package com.ccs.report.bo.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccs.report.dao.IBaseDAO;
import com.ccs.report.vo.InfoDateCountVO;

@Component("InfoCountByDateTask")
public class InfoCountByDateTask {

	private static final Logger LOG = Logger.getLogger(InfoCountByDateTask.class);

	@Autowired
	private IBaseDAO<InfoDateCountVO> infoDateCountDAO;

	@Scheduled(cron = "0 0 1 * * ?")
	public void doJob() {

		try {
			String sql = "select to_char(t.createtime,'yyyymmdd') as sdate, count(t.informationId) "
					+ "from hj_information t group by to_char(t.createtime, 'yyyymmdd') order by sdate desc";
			List<?> list = infoDateCountDAO.createSQLQuery(sql);
			List<InfoDateCountVO> beanList = new ArrayList<InfoDateCountVO>();
			for (Object obj : list) {
				Object[] arrs = (Object[]) obj;
				if(null != arrs[0]) {
					InfoDateCountVO bean = new InfoDateCountVO();
					bean.setDate(String.valueOf(arrs[0]));
					bean.setCount(String.valueOf(arrs[1]));
					beanList.add(bean);
				}
			}
			
			
			List<InfoDateCountVO> allList = infoDateCountDAO.getAll();
			for (InfoDateCountVO vo : allList) {
				infoDateCountDAO.delete(vo);
			}
			
			for (InfoDateCountVO vo : beanList) {
				infoDateCountDAO.save(vo);
			}

		} catch (Exception e) {
			LOG.error("count hist fail: " + e + e.getMessage());
		}
	}
	
}
