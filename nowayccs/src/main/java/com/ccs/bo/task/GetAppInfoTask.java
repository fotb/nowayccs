package com.ccs.bo.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.util.StringUtil;
import com.ccs.util.app.AppInfoResponse;
import com.ccs.util.app.Meta;
import com.ccs.web.domain.AppInfoBean;

import net.sf.json.JSONObject;

@Component("getAppInfoTask")
public class GetAppInfoTask {
	private static final Logger logger = Logger.getLogger(GetAppInfoTask.class);
	
	@Autowired
	private IAppReceiverBO appReceiverBO;

	@Scheduled(cron = "0 0/1 * * * ?")
	private void doJob() {
		try {
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost("http://test-jx-back.yuanduidui.cn/cms/orderConnect/search");

			/*
			 * JSONObject json = new JSONObject(); json.put("ordersState", 2);
			 * json.put("startTime", "2018-01-01 00:00:00"); json.put("endTime",
			 * "2018-07-27 23:59:59");
			 * 
			 * StringEntity s = new StringEntity(json.toString(), "UTF-8");
			 * s.setContentEncoding("UTF-8"); s.setContentType("application/json");
			 * 
			 * post.setEntity(s);
			 */
			HttpResponse res = httpclient.execute(post);
			String result = EntityUtils.toString(res.getEntity());// 返回json格式：

			JSONObject jsonObject = JSONObject.fromObject(result);
			Map<String, Class<AppInfoBean>> classMap = new HashMap<String, Class<AppInfoBean>>();
			classMap.put("list", AppInfoBean.class);
			AppInfoResponse bean = (AppInfoResponse) JSONObject.toBean(jsonObject, AppInfoResponse.class, classMap);
			Meta meta = bean.getData();
			List<AppInfoBean> list = meta.getList();
			
			List<AppInfoBean> manualList = new ArrayList<AppInfoBean>();
			List<AppInfoBean> appInfoList = new ArrayList<AppInfoBean>();
			for (AppInfoBean appInfoBean : list) {
				System.out.println(appInfoBean.getHelpAddr());
				//如果订单号和状态为空，则丢弃数据
				if(StringUtil.isNull(appInfoBean.getOrderNumber()) || StringUtil.isNull(appInfoBean.getOrderStatus())) {
					continue;
				}
				switch (appInfoBean.getOrderStatus()) {
				case "0":
					// do nothing
					break;
				case "2":
					// 内部人工派单
					manualList.add(appInfoBean);
					break;
				default:
					//insert to hj_information
					appInfoList.add(appInfoBean);
					break;
				}
			}

			if(!manualList.isEmpty()) {
				appReceiverBO.addManual(manualList);
				logger.info("成功从App系统导入" + manualList.size() +"条人工派单记录！");
			}
			if(!appInfoList.isEmpty()) {
				appReceiverBO.addInfo(appInfoList);
				logger.info("成功从App系统导入" + appInfoList.size() +"条记录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("GetAppInfoTask error: " + e.getMessage());
		}
	}
}
