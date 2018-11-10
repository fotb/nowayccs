import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccs.bean.EventBean;
import com.ccs.bean.EventReportBean;
import com.ccs.bean.RelavancyBean;
import com.ccs.vo.EventVO;

public class EventTest {

	public static void main(String[] args) {
		
		EventTest test = new EventTest();
		EventVO vo = new EventVO();
		
		vo.setEventSubject("嘉兴96345测试");
		vo.setEventDate("2018-11-28 00:08:08");
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
		vo.setSerialNumber(test.genCode());
		
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
			
	        StringEntity reqEntity = new StringEntity(param,Charset.forName("UTF-8"));
	        HttpPost httppost = new HttpPost(url);  
	        httppost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=\"UTF-8\"");
	        
	        post.setEntity(reqEntity);
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
