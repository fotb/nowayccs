package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ccs.bo.IEventBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.services.client.UnifiedDataDockingWebServiceProxy;
import com.ccs.services.vo.IssueRelatedPeople;
import com.ccs.services.vo.UnifiedDataDockingReturnVO;
import com.ccs.services.vo.UnifiedDataDockingVO;
import com.ccs.util.Constants;
import com.ccs.util.XmlUtil;
import com.ccs.vo.EventCategoryVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;


@Service("eventBO")
@Transactional
public class EventBOImpl implements IEventBO {
	
	private static final Logger logger = Logger.getLogger(EventBOImpl.class);
	
//	private static final String USER_ID = "bb4a9da366ed8dae0167068170db545b";
	
	private static final String FORMATE_CREATETIME = "yyyy-MM-dd HH:mm:ss";
	
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
		        final String key = "996ab5ba055127e6d781cd3f274897e3";
				
		        UnifiedDataDockingVO uddVO = new UnifiedDataDockingVO();
		        //uddVO.setKey(key);
		        uddVO.setSubject(vo.getEventSubject());
		        //uddVO.setOrgName("浙江省->嘉兴市->南湖区");
		        uddVO.setOrgName("330402");
		        uddVO.setOccurLocation(vo.getEventLocation());
		        uddVO.setOccurDate(vo.getEventDate());
		        IssueRelatedPeople people = new IssueRelatedPeople();
		        people.setIssueRelatedPeopleName(vo.getObjName());
		        people.setIssueRelatedPeopleTelephone(vo.getMobile());
		        List<IssueRelatedPeople> issueRelatedPeoples = new ArrayList<IssueRelatedPeople>();
		        issueRelatedPeoples.add(people);
		        uddVO.setIssueRelatedPeoples(issueRelatedPeoples);
		        
		        uddVO.setRelatePeopleCount(vo.getRelatePeopleCount());
		        EventCategoryVO ecVO = eventCategoryDAO.get(vo.getFirstCategoryId());
		        uddVO.setIssueBigTypeName("综治工作");

		        EventCategoryVO ecVO2 = eventCategoryDAO.get(vo.getSecondCategoryId());
		        uddVO.setIssueSmallTypeName(ecVO2.getName());
//		        uddVO.setIssueSmallTypeName("其他");
		        uddVO.setIssueContent(vo.getEventContent());
		        uddVO.setDataOrigin("嘉兴市南湖区96345");
		       // uddVO.setIssueAttachs(new ArrayList<IssueAttach>());
		        
		        UnifiedDataDockingWebServiceProxy proxy = new UnifiedDataDockingWebServiceProxy();
		        
		        String xml = XmlUtil.toXml(uddVO);
		        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><key>996ab5ba055127e6d781cd3f274897e3</key>" + xml + "</data>");
		        String result = proxy.addIssue("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><key>"+ key +"</key>" + xml + "</data>");
		        System.out.println(result);

		        UnifiedDataDockingReturnVO uddReturnVO = XmlUtil.toBean(result, UnifiedDataDockingReturnVO.class);
				if (UnifiedDataDockingReturnVO.RETURN_CODE_SUCCESS.equals(uddReturnVO.getResultCode())) {
					vo.setUpStatus(EventVO.UP_STATUS_1);
					vo.setSerialNumber(uddReturnVO.getIssueNew().getSerialNumber());
				} else {
					vo.setUpStatus(EventVO.UP_STATUS_0);
				}
		        
				eventDAO.update(vo);
			}catch(Exception e) {
				logger.error(e);
				System.out.println(e);
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
	/*@Override
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
            		buffer.append("处理时间：" + DateUtil.format(dt, FORMATE_CREATETIME));
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
	        
	        if(bean.isSuccess()){
	        	logger.info("success to query with serialNumber " + vo.getSerialNumber());
	        	 logger.error("query info success with result: " + result);
	        	 eventDAO.update(vo);
	        } else {
	            logger.info("fail to query with serialNumber: " + vo.getSerialNumber());
	        }
	        
		}catch(Exception e) {
			logger.error(e);
		}
		
	}*/
	@Override
	public void processEvent() throws Exception {
		final String hql = "from EventVO where status != ?";
		List<EventVO> eVOList = eventDAO.queryForObject(hql, new Object[] {"50"});
		for (EventVO eventVO : eVOList) {
//			queryEvent(eventVO);
		}
	}
}
