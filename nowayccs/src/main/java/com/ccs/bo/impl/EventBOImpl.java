package com.ccs.bo.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ccs.bean.EventBean;
import com.ccs.bean.EventReportBean;
import com.ccs.bean.RelavancyBean;
import com.ccs.bo.IEventBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.vo.EventCategoryVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;


@Service("eventBO")
@Transactional
public class EventBOImpl implements IEventBO {
	
	private static final Logger logger = Logger.getLogger(EventBOImpl.class);
	
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
		informatinDAO.saveOrUpate(vo);	
		eventVO.setInformationId(vo.getInfoId());
		eventVO.setCreateTime(new Date());
		eventDAO.save(eventVO);
		pushEvent(eventVO);
	}
	
	@Override
	public void pushEvent(EventVO vo) throws Exception {
			try {

		        
		        final String appKey = "KUAFWFVUSJOSCXUVWUBH";
				
		        //String url = "http://59.202.61.198:11100/api/cooperation/event/eventReport.json?bizContent=abc&appKey=abc";
		       
		        String url = "http://59.202.61.198:11100/api/cooperation/event/eventReport.json";
		        
		        EventBean bean = new EventBean();
		        bean.setEventContent(vo.getEventContent());
		        bean.setEventDate(vo.getEventDate());
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
		        //bean.setUserId(vo.get);
		        bean.setCreateDate(String.valueOf(vo.getCreateTime().getTime()));
		        //bean.setWhereTo("");
		        
		        List<RelavancyBean> relavancyList = new ArrayList<RelavancyBean>();
		        RelavancyBean rBean = new RelavancyBean();
		        rBean.setObjName(vo.getObjName());
		        rBean.setmPhone(vo.getMobile());
		        
		        
		        relavancyList.add(rBean);
		        bean.setRelavancyList(relavancyList);
		        
		        EventReportBean erBean = new EventReportBean();
		        erBean.setEvent(bean);
		        String json = JSONObject.toJSONString(erBean);
		        System.out.println(json);
//		        jsonObj.
		        
//		        JSONArray array = new JSONArray();
//		        for (AppInfoBean bean : list) {
//		        	JSONObject json = new JSONObject();
//					json.put("orderNumber", bean.getOrderNumber());
//					json.put("ordersType", bean.getOrdersType());
//					array.add(json);
//				}
		        
		        String param = "bizContent="+json+ "&appKey=" + appKey;
		        
		        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		        HttpPost post = new HttpPost(url);
				
		        StringEntity reqEntity = new StringEntity(param,Charset.forName("UTF-8"));
		        HttpPost httppost = new HttpPost(url);  
		        httppost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=\"UTF-8\"");
		        
		        post.setEntity(reqEntity);
		        HttpResponse res = httpclient.execute(post);
		        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
	            System.out.println(result);
//		        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
////		        	logger.info("success to update status： " + list.toString());
//		        } else {
//		            logger.info("fail to update status: " + result);
//		        }
	         
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
	}
}
