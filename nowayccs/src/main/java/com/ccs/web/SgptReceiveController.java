package com.ccs.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ccs.bo.IEventBO;
import com.ccs.util.AESUtil;
import com.ccs.vo.EventVO;

@RestController
public class SgptReceiveController {

	private static Logger log = Logger.getLogger(SgptReceiveController.class);

	@Autowired
	private IEventBO eventBO;

	@RequestMapping(value = "/platform/event/sendMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String updateEventStatus(@RequestParam String desParam) {
		String str = AESUtil.decrypt(desParam, EventVO.APPKEY);
		JSONObject jsonObj = JSONObject.parseObject(str);
		for (Object key : jsonObj.keySet()) {
			log.info(jsonObj.get(String.valueOf(key)));
		}
		String serialNumber = jsonObj.getString("serialNumber");
		String status = jsonObj.getString("status");
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			eventBO.updateEventStatus(serialNumber, status);
			returnMap.put("result", true);
		} catch (Exception e) {
			returnMap.put("result", false);
			returnMap.put("errDesc", "Update status error");
			returnMap.put("errCode", "Update status error");
		}
		JSONObject returnJsonObj = new JSONObject(returnMap);

		return returnJsonObj.toJSONString();
	}
}
