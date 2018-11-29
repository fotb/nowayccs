package com.ccs.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IElevBO;
import com.ccs.bo.IInformationBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.ComboBoxContainer;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.StringUtil;
import com.ccs.vo.ElevHelpInfoVO;
import com.ccs.vo.ElevatorVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.ElevatorHelpInfoDomain;

@Controller
@RequestMapping("/elevbackvst.do")
public class ElevatorBackVstController {
	
	
	@Autowired
	private IElevBO elevBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IInformationBO informationBO;
	

	@RequestMapping
	public String list( HttpSession session, ModelMap model) {
		return "elevator/backvst/list";
	}
	
	@RequestMapping(params = "action=get")
	public @ResponseBody List<ElevatorHelpInfoDomain> findElevHelpInfoByCreator(@RequestParam(value = "creator", required = false) String creator, HttpSession session) {
		
		List<ElevHelpInfoVO> elevHelpInfoVOList = new ArrayList<ElevHelpInfoVO>();
		if(StringUtil.isNotBlank(creator)) {
			elevHelpInfoVOList = elevBO.findElevHelpInfoByCreator(creator);
		} else {
			UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
			elevHelpInfoVOList = elevBO.findElevHelpInfoByCreator(user.getUserId());
		}
		
		List<ElevatorHelpInfoDomain> list = new ArrayList<ElevatorHelpInfoDomain>();
		for (ElevHelpInfoVO elevHelpInfoVO : elevHelpInfoVOList) {
			ElevatorVO elevatorVO = elevBO.getElevatorByPid(elevHelpInfoVO.getElevatorId());
			InformationVO infoVO = informationBO.findById(elevHelpInfoVO.getInformationId());
			ElevatorHelpInfoDomain domain = new ElevatorHelpInfoDomain();
			domain.setArriveTime(elevHelpInfoVO.getArriveTime());
			domain.setCasualty(elevHelpInfoVO.getCasualty());
			domain.setCreateTime(DateUtil.format(infoVO.getCreateTime(), "yyyyMMdd hh:mm:ss"));
			domain.setCreator(infoVO.getCreator());
			domain.setDealResult(elevHelpInfoVO.getDealResult());
			domain.setDeathToll(elevHelpInfoVO.getDeathToll());
			domain.setDeviceId(elevatorVO.getDeviceId());
			domain.setDispatchTime(elevHelpInfoVO.getDispatchTime());
			domain.setDutyResult(elevHelpInfoVO.getDutyResult());
			domain.setFinishTime(DateUtil.format(infoVO.getFinishTime(), "yyyyMMdd hh:mm:ss"));
			domain.setHelpAddr(infoVO.getHelpAddr());
			domain.setHelpArea(infoVO.getHelpArea());
			domain.setHelpContent(infoVO.getHelpContent());
			domain.setHelpDept(elevHelpInfoVO.getHelpDept());
			domain.setHelpGroup(infoVO.getHelpGroup());
			domain.setHelpMode(infoVO.getHelpMode());
			domain.setHelpName(infoVO.getHelpName());
			domain.setHelpTel(infoVO.getHelpTel());
			domain.setHelpType(infoVO.getHelpType());
			domain.setInfoId(infoVO.getInfoId());
			domain.setInjuries(elevHelpInfoVO.getInjuries());
			domain.setManufacturer(elevatorVO.getManufacturer());
			domain.setNextSurveyDate(elevatorVO.getNextSurveyDate());
			domain.setOtherPhone(elevHelpInfoVO.getReportPhone());
			domain.setPosition(elevatorVO.getPosition());
			domain.setReason(elevHelpInfoVO.getReason());
			domain.setReferNo(elevatorVO.getReferNo());
			domain.setReporter(elevHelpInfoVO.getReporter());
			domain.setReportingTime(elevHelpInfoVO.getReportingTime());
			domain.setReportPhone(elevHelpInfoVO.getReportPhone());
			domain.setSerialNumber(elevatorVO.getSerialNumber());
			domain.setServiceName(elevatorVO.getServiceName());
			domain.setStartTime(elevHelpInfoVO.getStartTime());
			domain.setStatus(infoVO.getStatus());
			domain.setSurveyDate(elevatorVO.getSurveyDate());
			domain.setTrapppedPerson(elevHelpInfoVO.getTrapppedPerson());
			domain.setType(elevatorVO.getType());
			domain.setUseDept(elevatorVO.getUseDept());
			domain.setPid(elevHelpInfoVO.getPid());
			list.add(domain);
		}
		
		return list;
	}
	
	
	@RequestMapping(params = "action=allOnJobUser")
	public @ResponseBody List<ComboBoxContainer> getAllOnJobUser(HttpSession session) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);

		List<ComboBoxContainer> comboBoxList = new ArrayList<ComboBoxContainer>();
		List<UserVO> list = userBO.findAllOnJob();
		for (UserVO userVO : list) {
			ComboBoxContainer comboBox = new ComboBoxContainer();
			comboBox.setText(userVO.getUserName());
			comboBox.setValue(userVO.getUserId());
			if(null != user && user.getUserId().equals(userVO.getUserId())) {
				comboBox.setSelected(true);
			}
			comboBoxList.add(comboBox);
		}
		
		return comboBoxList;
	}
	
	
	@RequestMapping(params = "action=back")
	public String  back(@RequestParam(value = "pid", required = false) String pid, HttpSession session, ModelMap model) {
		ElevHelpInfoVO elevHelpInfoVO = elevBO.getElevHelpInfoByPid(pid);
		ElevatorVO elevatorVO = elevBO.getElevatorByPid(elevHelpInfoVO.getElevatorId());
		InformationVO infoVO = informationBO.findById(elevHelpInfoVO.getInformationId());
		
		ElevatorHelpInfoDomain domain = new ElevatorHelpInfoDomain();
		domain.setArriveTime(elevHelpInfoVO.getArriveTime());
		domain.setCasualty(elevHelpInfoVO.getCasualty());
		domain.setCreateTime(DateUtil.format(infoVO.getCreateTime(), "yyyyMMdd hh:mm:ss"));
		domain.setCreator(infoVO.getCreator());
		domain.setDealResult(elevHelpInfoVO.getDealResult());
		domain.setDeathToll(elevHelpInfoVO.getDeathToll());
		domain.setDeviceId(elevatorVO.getDeviceId());
		domain.setDispatchTime(elevHelpInfoVO.getDispatchTime());
		domain.setDutyResult(elevHelpInfoVO.getDutyResult());
		domain.setFinishTime(DateUtil.format(infoVO.getFinishTime(), "yyyyMMdd hh:mm:ss"));
		domain.setHelpAddr(infoVO.getHelpAddr());
		domain.setHelpArea(infoVO.getHelpArea());
		domain.setHelpContent(infoVO.getHelpContent());
		domain.setHelpDept(elevHelpInfoVO.getHelpDept());
		domain.setHelpGroup(infoVO.getHelpGroup());
		domain.setHelpMode(infoVO.getHelpMode());
		domain.setHelpName(infoVO.getHelpName());
		domain.setHelpTel(infoVO.getHelpTel());
		domain.setHelpType(infoVO.getHelpType());
		domain.setInfoId(infoVO.getInfoId());
		domain.setInjuries(elevHelpInfoVO.getInjuries());
		domain.setManufacturer(elevatorVO.getManufacturer());
		domain.setNextSurveyDate(elevatorVO.getNextSurveyDate());
		domain.setOtherPhone(elevHelpInfoVO.getReportPhone());
		domain.setPosition(elevatorVO.getPosition());
		domain.setReason(elevHelpInfoVO.getReason());
		domain.setReferNo(elevatorVO.getReferNo());
		domain.setReporter(elevHelpInfoVO.getReporter());
		domain.setReportingTime(elevHelpInfoVO.getReportingTime());
		domain.setReportPhone(elevHelpInfoVO.getReportPhone());
		domain.setSerialNumber(elevatorVO.getSerialNumber());
		domain.setServiceName(elevatorVO.getServiceName());
		domain.setStartTime(elevHelpInfoVO.getStartTime());
		domain.setStatus(infoVO.getStatus());
		domain.setSurveyDate(elevatorVO.getSurveyDate());
		domain.setTrapppedPerson(elevHelpInfoVO.getTrapppedPerson());
		domain.setType(elevatorVO.getType());
		domain.setUseDept(elevatorVO.getUseDept());
		domain.setPid(elevHelpInfoVO.getPid());
		
		model.addAttribute("domain", domain);
		return "elevator/backvst/backelevator";
	}
}
