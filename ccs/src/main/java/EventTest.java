import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccs.bean.EventBean;
import com.ccs.bean.EventReportBean;
import com.ccs.bean.RelavancyBean;
import com.ccs.util.DateUtil;
import com.ccs.vo.EventVO;

public class EventTest {

	public static void main(String[] args) {
		
		EventTest test = new EventTest();
		EventVO vo = new EventVO();
		
		Date date = new Date();
		vo.setEventSubject("嘉兴96345测试");
		vo.setEventDate(String.valueOf(date.getTime()));
		vo.setEventLocation("测试地点");
		vo.setEventContent("测试内容");
		vo.setEventLevel("3");
		vo.setEventSource("6");
		vo.setIsImpPlase("0");
		vo.setRelatePeopleCount("1");
		vo.setFirstCategoryId("01");
		vo.setSecondCategoryId("01001");
		vo.setStatus("10");
		vo.setOrganizationId("330402ZF260000");
		vo.setObjName("测试人");
		vo.setMobile("13805730000");
		vo.setCreateTime(new Date());
		
		String serialNum = EventVO.EVENT_ORG_ID + DateUtil.format(new Date(), "yyMMdd") + vo.getSecondCategoryId() + test.genCode();
		vo.setSerialNumber(serialNum);
		
		try {
			test.pushEvent(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	 private String genCode() {
		// 字符串
	        String string = "";
	        // 循环得到10个字母
	        for (int i = 0; i < 7; i++) {
	            // 得到随机字母
	            char c = (char) ((Math.random() * 26) + 'A');
	            // 拼接成字符串
	            string += (c + "");
	        }
	       return string;
	 }
	
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
	        bean.setSecondCategoryId(vo.getSecondCategoryId());
	        bean.setIsImpplace(vo.getIsImpPlase());
	        bean.setLatiTude(vo.getLatitude());
	        bean.setLongiTude(vo.getLongitude());
	        bean.setMobile(vo.getMobile());
	        bean.setRelatePeopleCount(vo.getRelatePeopleCount());
	        bean.setStatus(vo.getStatus());
	        bean.setUserId("bb4a9da366ed8dae0167068170db545b");
	        bean.setCreateDate(String.valueOf(vo.getCreateTime().getTime()));
	        //bean.setWhereTo("");
	        
	        bean.setLatiTude("0");
	        bean.setLongiTude("0");
	        bean.setSerialNumber(vo.getSerialNumber());
	        bean.setWhereTo("赴县");
	        
	        List<RelavancyBean> relavancyList = new ArrayList<RelavancyBean>();
	        RelavancyBean rBean = new RelavancyBean();
	        rBean.setRclassIfIcation("1");
	        rBean.setRclassIfIcationId("-1");
	        rBean.setObjName(vo.getObjName());
	        rBean.setmPhone(vo.getMobile());
	        
	        
	        relavancyList.add(rBean);
	        bean.setRelavancyList(relavancyList);
	        
	        EventReportBean erBean = new EventReportBean();
	        erBean.setEvent(bean);
	        String json = JSONObject.toJSONString(erBean);
	        System.out.println(json);
//	        jsonObj.
	        
//	        JSONArray array = new JSONArray();
//	        for (AppInfoBean bean : list) {
//	        	JSONObject json = new JSONObject();
//				json.put("orderNumber", bean.getOrderNumber());
//				json.put("ordersType", bean.getOrdersType());
//				array.add(json);
//			}
	        
	        String param = "bizContent="+json+ "&appKey=" + appKey;
		        
	        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
	        HttpPost post = new HttpPost(url);
			
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        nvps.add(new BasicNameValuePair("bizContent", json));  
	        nvps.add(new BasicNameValuePair("appKey", appKey));
	        post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8")); 
	        
//	        StringEntity reqEntity = new StringEntity(param,Charset.forName("UTF-8"));
//	        reqEntity.
//	        HttpPost httppost = new HttpPost(url);  
	        post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=\"UTF-8\"");
	        
//	        post.setEntity(reqEntity);
	        HttpResponse res = httpclient.execute(post);
	        String result = EntityUtils.toString(res.getEntity());// 返回json格式：
            System.out.println(result);
//	        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
////	        	logger.info("success to update status： " + list.toString());
//	        } else {
//	            logger.info("fail to update status: " + result);
//	        }
         
		}catch(Exception e) {
			e.printStackTrace();
		}
}

}
