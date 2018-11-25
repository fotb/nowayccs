package com.ccs.web.domain;

import java.io.Serializable;

import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;

public class ShsResultDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1610825354942168895L;
	
	private LonelyHelpVO lhVO;
	
	private LonelyManInfoVO lmiVO;
	
	private PartyMemberForLonelyVO pmflVO;
	
	private String userId;
	
	private String userName;

	public LonelyHelpVO getLhVO() {
		return lhVO;
	}

	public void setLhVO(LonelyHelpVO lhVO) {
		this.lhVO = lhVO;
	}

	public LonelyManInfoVO getLmiVO() {
		return lmiVO;
	}

	public void setLmiVO(LonelyManInfoVO lmiVO) {
		this.lmiVO = lmiVO;
	}

	public PartyMemberForLonelyVO getPmflVO() {
		return pmflVO;
	}

	public void setPmflVO(PartyMemberForLonelyVO pmflVO) {
		this.pmflVO = pmflVO;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
