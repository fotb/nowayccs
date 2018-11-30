package com.ccs.bo;

import java.util.List;

import com.ccs.vo.ElevHelpInfoVO;
import com.ccs.vo.ElevatorVO;
import com.ccs.vo.InformationVO;

public interface IElevBO {

	public List<ElevatorVO> getElevator(String q);
	
	public void addElevHelp(InformationVO iVO, ElevatorVO eVO, ElevHelpInfoVO ehiVO) throws Exception;
	
	public ElevatorVO getElevatorByDeviceId(final String deviceId);
	
	public List<ElevHelpInfoVO> findElevHelpInfoByCreator(final String userId);
	
	public ElevatorVO getElevatorByPid(final String pid);
	
	public ElevHelpInfoVO getElevHelpInfoByPid(final String pid);
	
	public void updateElevHelpInfo(ElevHelpInfoVO elevHelpInfoVO, InformationVO iVO) throws Exception ;
	
}
