package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccs.bean.EventBean;
import com.ccs.bean.EventFlowDTO;
import com.ccs.bean.EventReportBean;
import com.ccs.bean.EventRequestBean;
import com.ccs.bean.RelavancyBean;
import com.ccs.bo.IEventBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.StringUtil;
import com.ccs.vo.EventCategoryVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;


@Service("eventBO")
@Transactional
public class EventBOImpl implements IEventBO {
	
	private static final Logger logger = Logger.getLogger(EventBOImpl.class);
	
	private static final String USER_ID = "bb4a9da366ed8dae0167068170db545b";
	
	@Autowired
	private IInformationDAO informatinDAO;

	@Autowired
	private IBaseDAO<EventCategoryVO> eventCategoryDAO;
	
	
	@Autowired
	private IBaseDAO<EventVO> eventDAO;
	
	@Override
	public List<EventCategoryVO> getEventCategory() throws Exception {
		final String sql = "select * from SGPT_EVENTCATEGORY t start with t.code='0' connect by (prior t.code) = t.parentcode";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>)eventCategoryDAO.createSQLQuery(sql, new Object[] {}, new Type[]{});
		List<EventCategoryVO> voList = new ArrayList<EventCategoryVO>();
		for (Object[] objs : list) {
			EventCategoryVO vo = new EventCategoryVO();
			vo.setPid(String.valueOf(objs[0]));
			vo.setName(String.valueOf(objs[1]));
			vo.setCode(String.valueOf(objs[2]));
			vo.setParentCode(String.valueOf(objs[3]));
			voList.add(vo);
		}
		return voList;
	}
	@Override
	public EventCategoryVO getEventCategory(String code) throws Exception {
		final String hql = "from EventCategoryVO where code = ?";
		List<EventCategoryVO> list = eventCategoryDAO.queryForObject(hql, new Object[] {code});
		return list.isEmpty() ? null : list.get(0);
	}
	
	
	@Override
	public void acceptSGPT(InformationVO vo, EventVO eventVO) throws Exception {
		vo.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		informatinDAO.saveOrUpate(vo);	
		eventVO.setInformationId(vo.getInfoId());
		eventVO.setCreateTime(new Date());
		eventVO.setRclassification("1");
		eventVO.setRclassificationId("-1");
		eventVO.setUpStatus(EventVO.UP_STATUS_0);
		eventDAO.save(eventVO);
		pushEvent(eventVO);
	}
	
	@Override
	public void pushEvent(EventVO vo) {
			try {
//		        final String appKey = "KUAFWFVUSJOSCXUVWUBH";
				
		        String url = "http://59.202.61.198:11100/api/cooperation/event/eventReport.json";
		        
		        EventBean bean = new EventBean();
		        bean.setEventContent(vo.getEventContent());
		        String eventDate = vo.getEventDate();
		        Date eDate = DateUtil.parse(eventDate, "yyyyMMdd HH:mm:ss");
		        bean.setEventDate(String.valueOf(eDate));
		        bean.setEventLevel(vo.getEventLevel());
		        bean.setEventLocation(vo.getEventLocation());
		        bean.setEventSource(vo.getEventSource());
		        bean.setEventSubject(vo.getEventSubject());
		        bean.setFirstCategoryId(vo.getFirstCategoryId());
		        bean.setIsImpplace(vo.getIsImpPlase());
		        bean.setLatiTude(vo.getLatitude());
		        bean.setLongiTude(vo.getLongitude());
		        bean.setMobile(vo.getMobile());
		        bean.setRelatePeopleCount(vo.getRelatePeopleCount());
		        bean.setStatus(vo.getStatus());
		        bean.setUserId(USER_ID); 
		        bean.setCreateDate(String.valueOf(vo.getCreateTime().getTime()));

		        bean.setLatiTude("0");
		        bean.setLongiTude("0");
		        bean.setSerialNumber(vo.getSerialNumber());
//		        bean.setWhereTo("赴县");
		        
		        List<RelavancyBean> relavancyList = new ArrayList<RelavancyBean>();
		        RelavancyBean rBean = new RelavancyBean();
		        rBean.setRclassIfIcation(vo.getRclassification());
		        rBean.setRclassIfIcationId(vo.getRclassificationId());
		        rBean.setObjName(vo.getObjName());
		        rBean.setmPhone(vo.getMobile());
		        
		        
		        relavancyList.add(rBean);
		        bean.setRelavancyList(relavancyList);
		        
		        EventReportBean erBean = new EventReportBean();
		        erBean.setEvent(bean);
		        String json = JSONObject.toJSONString(erBean);
		        System.out.println(json);

//		        String param = "bizContent="+json+ "&appKey=" + appKey;
		        
		        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		        HttpPost post = new HttpPost(url);
				
		        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		        nvps.add(new BasicNameValuePair("bizContent", json));  
		        nvps.add(new BasicNameValuePair("appKey", EventVO.APPKEY));
		        post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8")); 
		        
		        post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=\"UTF-8\"");
		        
		        HttpResponse res = httpclient.execute(post);
		        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	            
		        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		        	logger.info("success to upload with serialNumber " + vo.getSerialNumber());
		        	 logger.error("upload info fail with result: " + result);
		        	vo.setUpStatus(EventVO.UP_STATUS_1);
		        } else {
		            logger.info("fail to upload with serialNumber: " + vo.getSerialNumber());
		            vo.setUpStatus(EventVO.UP_STATUS_0);
		        }
		        eventDAO.update(vo);
			}catch(Exception e) {
				logger.error(e);
			}
	}
	@Override
	public void updateEventStatus(String serialNum, String status) throws Exception {
		final String hql = "from EventVO where serialNumber = ?";
		List<EventVO> voList = eventDAO.queryForObject(hql, new Object[] {serialNum});
		for (EventVO eventVO : voList) {
			eventVO.setStatus(status);
			eventDAO.update(eventVO);
		}
	}
	@Override
	public List<EventVO> getUnUploadEvent() {
		final String hql = "from EventVO where upStatus = ?";
		return eventDAO.queryForObject(hql, new Object[] {EventVO.UP_STATUS_0});
	}
	@Override
	public void queryEvent(EventVO vo) {
		try {
//	        final String appKey = "KUAFWFVUSJOSCXUVWUBH";
			
	        String url = "http://59.202.61.198:11100/api/cooperation/event/detail.json";
	        
	        JSONObject jsonObj = new JSONObject();
	        jsonObj.put("serialNumber", vo.getSerialNumber());
	        String json = JSONObject.toJSONString(jsonObj);
	        System.out.println(json);

//	        String param = "bizContent="+json+ "&appKey=" + appKey;
	        
	        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	        HttpPost post = new HttpPost(url);
			
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("bizContent", json));  
	        nvps.add(new BasicNameValuePair("appKey", EventVO.APPKEY));
	        post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8")); 
	        
	        post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=\"UTF-8\"");
	        
	        HttpResponse res = httpclient.execute(post);
	        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
            
	        EventRequestBean bean = JSON.parseObject(result, EventRequestBean.class);

	        vo.setStatus(bean.getResponse().getModule().getStatus());
            List<EventFlowDTO> efDTOList = bean.getResponse().getModule().getEventFlowDTOList();
            StringBuffer buffer = new StringBuffer();
            for (EventFlowDTO eventFlowDTO : efDTOList) {
            	String processDate = eventFlowDTO.getProcessDate();
            	if(StringUtil.isNotEmpty(processDate)) {
            		Date dt = new Date(Long.parseLong(processDate));
            		buffer.append("处理时间：" + DateUtil.format(dt, "yyyy-MM-dd HH:mm:ss"));
            	} else {
            		buffer.append("处理时间：--");
            	}
            	buffer.append("<br/>");	
            	buffer.append("处理部门：" + eventFlowDTO.getOrganizationName());
            	buffer.append("<br/>");
            	buffer.append("处理人:" + eventFlowDTO.getUserName());
            	buffer.append("<br/>");
            	buffer.append("处理意见:" + eventFlowDTO.getSuggestion());
            	buffer.append("<br/><br/><br/>");
			}
            
            vo.setSuggestion(buffer.toString());
	        
	        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        	logger.info("success to query with serialNumber " + vo.getSerialNumber());
	        	 logger.error("query info fail with result: " + result);
	        	 eventDAO.update(vo);
	        } else {
	            logger.info("fail to query with serialNumber: " + vo.getSerialNumber());
	        }
	        
		}catch(Exception e) {
			logger.error(e);
		}
		
	}
	@Override
	public void processEvent() throws Exception {
		final String hql = "from EventVO where t.status != ?";
		List<EventVO> eVOList = eventDAO.queryForObject(hql, new Object[] {"50"});
		for (EventVO eventVO : eVOList) {
			queryEvent(eventVO);
		}
	}
}
