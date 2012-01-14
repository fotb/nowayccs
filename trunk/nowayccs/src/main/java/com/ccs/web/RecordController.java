package com.ccs.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAgentBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IInfoSearchBO;
import com.ccs.bo.IInformationBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.icd.bo.IRecordInfoBO;
import com.ccs.icd.util.IcdSpringUtil;
import com.ccs.icd.vo.RecordInfoVO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PropertyLoad;
import com.ccs.util.StringUtil;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.AgentVO;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.ReferInformationVO;
import com.ccs.vo.VolunteerVO;

@Controller
@RequestMapping("/record.do")
public class RecordController {

	private static final Logger logger = Logger.getLogger(RecordController.class);

	private static final String RECORD_SERVER_HOST = "record.server.host";
	private static final String RECORD_SERVER_PORT = "record.server.port";
	private static final String RECORD_LOCALE_PATH = "record.locale.path";
	
	@Autowired
	private IInformationBO informationBO;
	
	@Autowired
	private IInfoSearchBO infoSearchBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IVolunteerBO volunteerBO;
	
	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private IAgentBO agentBO;
	
	@RequestMapping
	public String playRecord(String infoId, ModelMap model) {
		InformationVO infoVO = informationBO.findById(infoId);
		if(Constants.INFOMATION_HELPTYPE_LIFE.equals(infoVO.getHelpType())) {
			return lifeInfo(infoId, model);
		} else if(Constants.INFOMATION_HELPTYPE_AFFAIR.equals(infoVO.getHelpType())) {
			return affairInfo(infoId, model);
		} else if(Constants.INFOMATION_HELPTYPE_REFER.equals(infoVO.getHelpType())) {
			return referInfo(infoId, model);
		} else{// if(Constants.INFOMATION_HELPTYPE_FERTILITY.equals(infoVO.getHelpType())) {
			return productivityInfo(infoId, model);
		}
	}

	public String lifeInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		final List<String> recordUrlList = getRecordUrls(infoVO);
		
		model.addAttribute("recordUrlList", recordUrlList);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		LifeInformationVO lifeInfoVO = infoSearchBO.findLifeInfoByInfoId(infoId);
		if(!Constants.SYS_INFOMATION_STATES_DB.equals(infoVO.getStatus()) && null != lifeInfoVO) {
			if(Constants.LIFEINFOMATION_RECEIVETYPE_ZYZ.equals(lifeInfoVO.getReceiverType())) {
				VolunteerVO volunteerVO = volunteerBO.findById(lifeInfoVO.getReceiverId());
				model.addAttribute("vltVO", volunteerVO);
			} else {
				EntpriseVO entpriseVO = entpriseBO.findEntByEntpriseId(lifeInfoVO.getReceiverId());
				model.addAttribute("entVO", entpriseVO);
			}
			
			model.addAttribute("lifeInfoVO", lifeInfoVO);
			
			if(null != lifeInfoVO.getCaller()) {
				model.addAttribute("caller", userBO.findById(lifeInfoVO.getCaller()));
			}
		}
		return "record/lifeinfo";
	}

	private List<String> getRecordUrls(InformationVO infoVO) {
		String callId = infoVO.getCallId();
		List<String> recordUrlList = new ArrayList<String>();
		
		IRecordInfoBO recordInfoBO = (IRecordInfoBO) IcdSpringUtil.getBean("recordInfoBO");
		
	
		AgentVO agentVO = agentBO.findById(infoVO.getCreator());
//		if(!StringUtil.isNull(callId)) {
//			callId = callId.substring(0, callId.length() - 4);
//			
//			callId = callId.replace("_", "-");
//			Calendar calendar = new GregorianCalendar();
//			int year = calendar.get(Calendar.YEAR);
//			RecordInfoVO recordInfoVO = recordInfoBO.findById(callId, year);
//			if(null != recordInfoVO) {
//				String recordUrl = PropertyLoad.getInstance().getString("record.http.url");
//				final String fileName = recordInfoVO.getFileName();
//				String url = DateUtil.format(infoVO.getCreateTime(), "yyyyMMdd") + "/" + agentVO.getWorkNo() + "/" + fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
//				recordUrl = recordUrl + url;
//				recordUrlList.add(recordUrl);
//			}
//		} else {
			String callerNo = infoVO.getHelpTel();
			if(callerNo.contains(",")) {
				callerNo = callerNo.substring(0, callerNo.indexOf(",") );
			}
			
			
			final String beginTime = DateUtil.format(infoVO.getCreateTime(), "yyyyMMddHHmm");
			final String yearStr = DateUtil.format(infoVO.getCreateTime(), "yyyy");
			int year = Integer.parseInt(yearStr);
			List<RecordInfoVO> list = recordInfoBO.findRecordInfo(callerNo, agentVO.getWorkNo(), beginTime, year);
			for (RecordInfoVO recordInfoVO : list) {
				String fileName = recordInfoVO.getFileName();
//				if(fileName.contains("\\")) {
//					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
//				}
				String url = DateUtil.format(infoVO.getCreateTime(), "yyyyMMdd") + "/" + agentVO.getWorkNo() + "/" + fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
				final String recordUrl = PropertyLoad.getInstance().getString("record.http.url") + url;
				recordUrlList.add(recordUrl);
			}
//		}
		return recordUrlList;
	}
	
	public String referInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		final List<String> recordUrlList = getRecordUrls(infoVO);
		
		model.addAttribute("recordUrlList", recordUrlList);
		
		ReferInformationVO referInfoVO = infoSearchBO.findReferInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("referInfoVO", referInfoVO);
		
		model.addAttribute("dealer", userBO.findById(referInfoVO.getDealer()));
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		return "record/referinfo";
	}
	
	public String productivityInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		final List<String> recordUrlList = getRecordUrls(infoVO);
		
		model.addAttribute("recordUrlList", recordUrlList);
		
		ReferInformationVO referInfoVO = infoSearchBO.findReferInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("referInfoVO", referInfoVO);
		
		model.addAttribute("dealer", userBO.findById(referInfoVO.getDealer()));
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		return "record/productivityinfo";
	}
	
	
	public String affairInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		final List<String> recordUrlList = getRecordUrls(infoVO);
		
		model.addAttribute("recordUrlList", recordUrlList);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		
		AffairInformationVO affairInfoVO = infoSearchBO.findAffairInfoByInfoId(infoId);
		
		if(null != affairInfoVO) {
			model.addAttribute("affairInfoVO", affairInfoVO);
			
			if(null != affairInfoVO.getPrincipal()) {
				model.addAttribute("caller", userBO.findById(affairInfoVO.getPrincipal()));
			}
		}
		return "record/affairinfo";
	}
	
	
	@RequestMapping(params = "action=download", method = RequestMethod.GET)
	public @ResponseBody String downloadRecordFile(String infoId, ModelMap model) {
		String recordFilePath = "";
		InformationVO infoVO = informationBO.findById(infoId);
		try {
			if (Constants.SYS_YESNO_YES.equals(infoVO.getRecordFlag())) {
				recordFilePath = download(infoVO);
			}
		} catch (Exception e) {
			logger.error("Download record file error: " + e.getMessage(), e);
		}
		return recordFilePath;
	}

	private String download(InformationVO infoVO) throws ClientProtocolException, IOException {

		PropertyLoad property = PropertyLoad.getInstance();
		final String localePath = property.getString(RECORD_LOCALE_PATH);

		HttpClient httpClient = new DefaultHttpClient();
		HttpHost httpHost = new HttpHost(
				property.getString(RECORD_SERVER_HOST),
				Integer.parseInt(property.getString(RECORD_SERVER_PORT)));

		final String targetDate = DateUtil.format(infoVO.getCreateTime(),
				"yyyy-MM-dd");
		final String targetPath = "/" + targetDate + "/" + infoVO.getCreator()
				+ "/" + infoVO.getRecordFileName();

		HttpGet httpGet = new HttpGet(targetPath);

		HttpResponse response = httpClient.execute(httpHost, httpGet);
		if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				System.out.println(entity.getContentType());
				System.out.println(entity.isStreaming());
				File storeFile = new File(localePath + "/"
						+ infoVO.getRecordFileName());
				FileOutputStream output = new FileOutputStream(storeFile);
				InputStream input = entity.getContent();
				byte b[] = new byte[1024];
				int j = 0;
				while ((j = input.read(b)) != -1) {
					output.write(b, 0, j);
				}
				output.flush();
				output.close();
			}
		}
		return localePath + "/" + infoVO.getRecordFileName();
	}
}
