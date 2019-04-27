import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.ccs.services.client.UnifiedDataDockingWebServiceProxy;
import com.ccs.services.vo.IssueRelatedPeople;
import com.ccs.services.vo.UnifiedDataDockingReturnVO;
import com.ccs.services.vo.UnifiedDataDockingVO;
import com.ccs.services.client.UnifiedDataDockingWebServiceProxy;
import com.ccs.services.vo.IssueAttach;
import com.ccs.services.vo.IssueRelatedPeople;
import com.ccs.services.vo.UnifiedDataDockingReturnVO;
import com.ccs.services.vo.UnifiedDataDockingVO;
import com.ccs.util.DateUtil;
import com.ccs.util.XmlUtil;
import com.ccs.vo.EventVO;

public class EventTest {

	public static void main(String[] args) {
		
		EventTest test = new EventTest();
		EventVO vo = new EventVO();
		
		Date date = new Date();
		vo.setEventSubject("嘉兴96345测试");
		vo.setEventDate(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm"));
		vo.setEventLocation("测试地点");
		vo.setEventContent("测试内容");
		vo.setEventLevel("3");
		vo.setEventSource("6");
		vo.setIsImpPlase("0");
		vo.setRelatePeopleCount("1");
		vo.setFirstCategoryId("综治工作");
		vo.setSecondCategoryId("其他");
		vo.setStatus("10");
		vo.setOrganizationId("330402ZF260000");
		vo.setObjName("测试人");
		vo.setMobile("13805730000");
		vo.setCreateTime(new Date());
		
		
		try {
			test.pushEvent(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void pushEvent(EventVO vo) {
			try {
		        final String key = "996ab5ba055127e6d781cd3f274897e3";
				
		        UnifiedDataDockingVO uddVO = new UnifiedDataDockingVO();
		        //uddVO.setKey(key);
		        uddVO.setSubject(vo.getEventSubject());
		        //uddVO.setOrgName("浙江省->嘉兴市->南湖区");
//		        uddVO.setOrgName("330402");
		        uddVO.setOrgName("333203");
		        uddVO.setOccurLocation(vo.getEventLocation());
		        uddVO.setOccurDate(vo.getEventDate());
		        IssueRelatedPeople people = new IssueRelatedPeople();
		        people.setIssueRelatedPeopleName(vo.getObjName());
		        people.setIssueRelatedPeopleTelephone(vo.getMobile());
		        List<IssueRelatedPeople> issueRelatedPeoples = new ArrayList<IssueRelatedPeople>();
		        issueRelatedPeoples.add(people);
		        uddVO.setIssueRelatedPeoples(issueRelatedPeoples);
		        
		        uddVO.setRelatePeopleCount(vo.getRelatePeopleCount());
		        uddVO.setIssueBigTypeName(vo.getFirstCategoryId());
		        uddVO.setIssueSmallTypeName(vo.getSecondCategoryId());
		        
		        uddVO.setIssueContent(vo.getEventContent());
		        uddVO.setDataOrigin("嘉兴市南湖区96345");
		       // uddVO.setIssueAttachs(new ArrayList<IssueAttach>());
		        
		        UnifiedDataDockingWebServiceProxy proxy = new UnifiedDataDockingWebServiceProxy();
		        
		        String xml = XmlUtil.toXml(uddVO);
		        System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><key>996ab5ba055127e6d781cd3f274897e3</key>" + xml + "</data>");
		        String result = proxy.addIssue("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data><key>996ab5ba055127e6d781cd3f274897e3</key>" + xml + "</data>");
		        System.out.println(result);
		        
		        UnifiedDataDockingReturnVO uddReturnVO = XmlUtil.toBean(result, UnifiedDataDockingReturnVO.class);
				if (UnifiedDataDockingReturnVO.RETURN_CODE_SUCCESS.equals(uddReturnVO.getResultCode())) {
					vo.setUpStatus(EventVO.UP_STATUS_1);
					vo.setSerialNumber(uddReturnVO.getIssueNew().getSerialNumber());
				} else {
					vo.setUpStatus(EventVO.UP_STATUS_0);
				}
		        
		      //  eventDAO.update(vo);
			}catch(Exception e) {
				System.out.println(e);
			}
	}

}
