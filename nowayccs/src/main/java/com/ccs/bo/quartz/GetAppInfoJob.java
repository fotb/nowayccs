package com.ccs.bo.quartz;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.util.StringUtil;
import com.ccs.util.app.AppInfoResponse;
import com.ccs.util.app.Meta;
import com.ccs.web.domain.AppInfoBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class GetAppInfoJob implements Job {
	private static final Logger logger = Logger.getLogger(GetAppInfoJob.class);
	
	@Autowired
	private IAppReceiverBO appReceiverBO;
	
	private void updateStatus(List<AppInfoBean> list) {
		try {
	        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	        HttpPost post = new HttpPost("http://jxback.jx96345.cn/cms/orderConnect/verifyOrder");
			
	        
	        JSONArray array = new JSONArray();
	        for (AppInfoBean bean : list) {
	        	JSONObject json = new JSONObject();
				json.put("orderNumber", bean.getOrderNumber());
				json.put("ordersType", bean.getOrdersType());
				array.add(json);
			}
			
			StringEntity s = new StringEntity(array.toString(), Charset.forName("UTF-8"));
	        s.setContentEncoding("UTF-8");
	        s.setContentType("application/json");//发送json数据需要设置contentType
			
	        post.setEntity(s);
	        HttpResponse res = httpclient.execute(post);
	        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
//            System.out.println(result);
	        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        	logger.info("success to update status： " + list.toString());
	        } else {
	            logger.info("fail to update status: " + result);
	        }
         
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost("http://jxback.jx96345.cn/cms/orderConnect/search");

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
			
			updateStatus(list);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("GetAppInfoTask error: " + e.getMessage());
		}
	}
	
}
