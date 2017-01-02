package com.ccs.bo;

import java.util.Date;

public interface IUserStatusBO {
	public void updateUserStatus(final String userId, final String status, final String agentStatus, final String phoneNo, final String sessionId);
	
	public void updateTimeoutUserStatus(final Date curDate);
}
