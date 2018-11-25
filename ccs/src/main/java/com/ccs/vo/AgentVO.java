package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_AGENT")
public class AgentVO implements Serializable {

	private static final long serialVersionUID = 7161972225778647045L;

	@Id
	@Column(name = "USERID")
	private String userId;

	@Column(name = "WORKNO")
	private String workNo;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "AGENTTYPE")
	private String agentType;

	@Column(name = "CARDTYPE")
	private String cardType;

	@Column(name = "MAINCCSIP")
	private String mainCcsIp;

	@Column(name = "BACKCCSIP")
	private String backCcsIp;

	@Column(name = "TARGETDEVICE")
	private String targetDevice;

	@Column(name = "SERVERTYPE")
	private String serverType;

	@Column(name = "AUTOANSWER")
	private boolean autoAnswer;

	@Column(name = "AUTORELEASE")
	private boolean autoRelease;

	@Column(name = "AUTORECONNECT")
	private boolean autoReconnect;

	@Column(name = "HAVEBELL")
	private boolean haveBell;

	@Column(name = "BELLTIME")
	private String bellTime;

	@Column(name = "FREESTATUS")
	private boolean freeStatus;

	@Column(name = "MEDIAPLAY")
	private boolean mediaPlay;

	@Column(name = "MEDIAFILENAME")
	private String mediaFilename;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMainCcsIp() {
		return mainCcsIp;
	}

	public void setMainCcsIp(String mainCcsIp) {
		this.mainCcsIp = mainCcsIp;
	}

	public String getBackCcsIp() {
		return backCcsIp;
	}

	public void setBackCcsIp(String backCcsIp) {
		this.backCcsIp = backCcsIp;
	}

	public String getTargetDevice() {
		return targetDevice;
	}

	public void setTargetDevice(String targetDevice) {
		this.targetDevice = targetDevice;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	

	public boolean isAutoAnswer() {
		return autoAnswer;
	}

	public void setAutoAnswer(boolean autoAnswer) {
		this.autoAnswer = autoAnswer;
	}

	public boolean isAutoRelease() {
		return autoRelease;
	}

	public void setAutoRelease(boolean autoRelease) {
		this.autoRelease = autoRelease;
	}

	public boolean isAutoReconnect() {
		return autoReconnect;
	}

	public void setAutoReconnect(boolean autoReconnect) {
		this.autoReconnect = autoReconnect;
	}

	public boolean isHaveBell() {
		return haveBell;
	}

	public void setHaveBell(boolean haveBell) {
		this.haveBell = haveBell;
	}

	public String getBellTime() {
		return bellTime;
	}

	public void setBellTime(String bellTime) {
		this.bellTime = bellTime;
	}

	public boolean isFreeStatus() {
		return freeStatus;
	}

	public void setFreeStatus(boolean freeStatus) {
		this.freeStatus = freeStatus;
	}

	public boolean isMediaPlay() {
		return mediaPlay;
	}

	public void setMediaPlay(boolean mediaPlay) {
		this.mediaPlay = mediaPlay;
	}

	public String getMediaFilename() {
		return mediaFilename;
	}

	public void setMediaFilename(String mediaFilename) {
		this.mediaFilename = mediaFilename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgentVO other = (AgentVO) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
