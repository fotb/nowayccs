package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IUserStatusBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.util.DateUtil;
import com.ccs.vo.UserStatusHistVO;
import com.ccs.vo.UserStatusVO;

@Service("userStatusBO")
public class UserStatusBOImpl implements IUserStatusBO {

	private static final Logger logger = Logger.getLogger(UserStatusBOImpl.class);

	@Autowired
	private IBaseDAO<UserStatusVO> userStatusDAO;

	@Autowired
	private IBaseDAO<UserStatusHistVO> userStatusHistDAO;

	@Override
	@Transactional
	public void updateUserStatus(String userId, String status, String sessionId) {
		try {
			// query if user status is exist
			List<UserStatusVO> usVOList = userStatusDAO.queryForObject("from UserStatusVO where userId = ?",
					new Object[] { userId });

			Date curDate = DateUtil.getNowDate();
			if (usVOList.isEmpty()) {
				UserStatusVO usVO = new UserStatusVO();
				usVO.setUserId(userId);
				usVO.setCreateTime(curDate);
				usVO.setStatus(status);
				usVO.setSessionId(sessionId);
				usVO.setLastHbDt(curDate);
				usVO.setUpdateDT(curDate);
				usVO.setDeleteFlag(usVO.DELETE_FLAG_NO);
				userStatusDAO.save(usVO);
			} else {
				UserStatusVO usVO = usVOList.get(0);
				usVO.setStatus(status);
				usVO.setSessionId(sessionId);
				usVO.setLastHbDt(curDate);
				usVO.setUpdateDT(curDate);
				userStatusDAO.update(usVO);

				UserStatusHistVO ushVO = new UserStatusHistVO();
				ushVO.setUserId(usVO.getUserId());
				ushVO.setSessionId(usVO.getSessionId());
				ushVO.setLastHbDt(usVO.getLastHbDt());
				ushVO.setStatus(usVO.getStatus());
				ushVO.setCreateTime(usVO.getCreateTime());
				ushVO.setUpdateDT(usVO.getUpdateDT());
				ushVO.setDeleteFlag(usVO.getDeleteFlag());
				ushVO.setLastHandler(usVO.getLastHandler());
				userStatusHistDAO.save(ushVO);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void updateTimeoutUserStatus(Date curDate) {
		try {
			// query if user status is exist
			List<UserStatusVO> usVOList = userStatusDAO.queryForObject("from UserStatusVO where status = ?",
					new Object[] { UserStatusVO.STATUS_1 });

			for (UserStatusVO userStatusVO : usVOList) {
				Date startDate = userStatusVO.getLastHbDt();
				int minuts = DateUtil.getIntervalMinutes(startDate, curDate);

				if (minuts > 5) {
					userStatusVO.setStatus(UserStatusVO.STATUS_0);
					userStatusDAO.update(userStatusVO);
					UserStatusHistVO ushVO = new UserStatusHistVO();
					ushVO.setUserId(userStatusVO.getUserId());
					ushVO.setSessionId(userStatusVO.getSessionId());
					ushVO.setLastHbDt(userStatusVO.getLastHbDt());
					ushVO.setStatus(userStatusVO.getStatus());
					ushVO.setCreateTime(userStatusVO.getCreateTime());
					ushVO.setUpdateDT(userStatusVO.getUpdateDT());
					ushVO.setDeleteFlag(userStatusVO.getDeleteFlag());
					ushVO.setLastHandler(userStatusVO.getLastHandler());
					userStatusHistDAO.save(ushVO);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
  	}
}
