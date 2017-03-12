package com.ccs.report.bo.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccs.report.dao.IBaseDAO;
import com.ccs.report.vo.HistCountVO;

@Component("histCountTask")
public class HistCountTask {

	private static final Logger LOG = Logger.getLogger(HistCountTask.class);

	@Autowired
	private IBaseDAO<HistCountVO> histCountDAO;

	@Scheduled(cron = "0 0 2 * * ?")
	public void doJob() {

		try {
			String sql = "select to_char(t.createtime,'yyyy') as callyear, count(t.informationId) "
					+ "from hj_information t group by to_char(t.createtime, 'yyyy') order by callyear desc";
			List<?> list = histCountDAO.createSQLQuery(sql);
			List<HistCountVO> beanList = new ArrayList<HistCountVO>();
			for (Object obj : list) {
				Object[] arrs = (Object[]) obj;
				if(null != arrs[0]) {
					HistCountVO bean = new HistCountVO();
					bean.setYear(String.valueOf(arrs[0]));
					bean.setCount(String.valueOf(arrs[1]));
					beanList.add(bean);
				}
			}
			
			List<HistCountVO> voList = histCountDAO.getAll();
			Map<String, HistCountVO> map = new HashMap<String, HistCountVO>();
			for (HistCountVO vo : voList) {
				map.put(vo.getYear(), vo);
			}
			
			for (HistCountVO vo : beanList) {
				if(null != vo.getYear()) {
					if(map.containsKey(vo.getYear())) {
						HistCountVO hcVO = map.get(vo.getYear());
						hcVO.setCount(vo.getCount());
						histCountDAO.update(hcVO);
					} else {
						histCountDAO.save(vo);
					}
				}
			}

		} catch (Exception e) {
			LOG.error("count hist fail: " + e + e.getMessage());
		}
	}

}
