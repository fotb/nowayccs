import java.util.ArrayList;
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
import com.ccs.util.AESUtil;
import com.ccs.vo.EventVO;

public class MessagePrase {

	public static void main(String[] args) {
		try {
		String serialNum = "330402ZF26000018111001001ZCWYSUW";
		
		String str = "{\"serialNumber\":\"330402ZF26000018111001001ZCWYSUW\",\"status\":\"36\"}";
		String encryptStr = AESUtil.encrypt(str, EventVO.APPKEY);
		System.out.println(encryptStr);
		
		String decryptStr = AESUtil.decrypt(encryptStr, EventVO.APPKEY);
		System.out.println(decryptStr);

		
		
       
        String url = "http://localhost:9090/ccs/platform/event/sendMessage";
        
        
//        String param = "desParam="+encryptStr;
	        
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
		
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("desParam", encryptStr));  
        post.setEntity(new UrlEncodedFormEntity(nvps,"utf-8")); 
        
        post.addHeader("Content-Type","application/x-www-form-urlencoded; charset=\"UTF-8\"");
        
        HttpResponse res = httpclient.execute(post);
        String result = EntityUtils.toString(res.getEntity());
        System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
