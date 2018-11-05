package com.ccs.bo.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
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

import com.ccs.bo.IEventBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.vo.EventCategoryVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;
import com.ccs.web.domain.AppInfoBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		eventDAO.save(eventVO);
	}
	@Override
	public void pushEvent(EventVO vo) throws Exception {
			try {
		        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		        HttpPost post = new HttpPost("http://jxback.jx96345.cn/cms/orderConnect/verifyOrder");
				
		        String url = "http://59.202.61.198:11100/api/cooperation/event/eventReport.json?bizContent=abc&appKey=abc";
		       
		        
		        JSONArray array = new JSONArray();
//		        for (AppInfoBean bean : list) {
//		        	JSONObject json = new JSONObject();
//					json.put("orderNumber", bean.getOrderNumber());
//					json.put("ordersType", bean.getOrdersType());
//					array.add(json);
//				}
				
				StringEntity s = new StringEntity(array.toString(), Charset.forName("UTF-8"));
		        s.setContentEncoding("UTF-8");
		        s.setContentType("application/json");//发送json数据需要设置contentType
				
		        post.setEntity(s);
		        HttpResponse res = httpclient.execute(post);
		        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
//	            System.out.println(result);
		        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//		        	logger.info("success to update status： " + list.toString());
		        } else {
		            logger.info("fail to update status: " + result);
		        }
	         
			}catch(Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
	}
}
