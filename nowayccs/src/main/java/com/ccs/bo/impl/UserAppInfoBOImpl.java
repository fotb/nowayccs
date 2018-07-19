package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IUserAppInfoBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.util.DateUtil;
import com.ccs.vo.AppReceiverHistVO;
import com.ccs.vo.AppReceiverVO;
import com.ccs.vo.UserAppInfoVO;
import com.ccs.vo.UserStatusVO;
import com.ccs.web.domain.CountBean;

@Service("userAppInfoBO")
@Transactional
public class UserAppInfoBOImpl implements IUserAppInfoBO {
	@Autowired
	private IBaseDAO<UserAppInfoVO> userAppInfoDAO;

	@Autowired
	private IBaseDAO<AppReceiverVO> appReceiverDAO;

	@Autowired
	private IBaseDAO<AppReceiverHistVO> appReceiverHistDAO;

	@Autowired
	private IBaseDAO<UserStatusVO> userStatusDAO;

	@Override
	public void create(List<UserAppInfoVO> list) throws Exception {
		for (UserAppInfoVO userAppInfoVO : list) {
			userAppInfoDAO.saveOrUpdate(userAppInfoVO);
		}
	}

	@Override
	public List<UserAppInfoVO> findByUserId(String userId) throws Exception {
		String hql = "from UserAppInfoVO where userid = ?";
		return userAppInfoDAO.queryForObject(hql, new Object[] { userId });
	}

	@Override
	public List<UserAppInfoVO> findByAppInfoId(String appInfoId) throws Exception {
		final String hql = "from UserAppInfoVO where appInfoId = ?";
		return userAppInfoDAO.queryForObject(hql, new Object[] { appInfoId });
	}

	@Override
	public void delete(UserAppInfoVO vo) throws Exception {
		userAppInfoDAO.delete(vo);
	}

	@Override
	public void updateUserAppInfo() throws Exception {
		// TODO 1. 找出所有未处理的信息，派单分配的用户当前是否在线，如果不在线，则删除重新分配
		// TODO 2. 搜索APP_RECEIVER表，找出所有未处理记录
		// 3. 找出所有在线用户，从当天分配数量的多少分配，先分配给当天受理最少少的话务员，以此递增，知道分配完为止；
		// 4.
		// 找出所有在线接线员
		List<UserStatusVO> userStatusList = userStatusDAO.queryForObject("from UserStatusVO where status = ?",
				new Object[] { UserStatusVO.STATUS_1 });
		if (userStatusList.size() > 0) {
			Map<String, UserStatusVO> onlineUserMap = new HashMap<String, UserStatusVO>();
			for (UserStatusVO userStatusVO : userStatusList) {
				onlineUserMap.put(userStatusVO.getUserId(), userStatusVO);
			}
			// 查找所有处理中app求助，如果分配的接线员不在线，则删除记录，重新分配
			List<UserAppInfoVO> inprocessInfoList = userAppInfoDAO.getAll();
			for (UserAppInfoVO userAppInfoVO : inprocessInfoList) {
				if (!onlineUserMap.containsKey(userAppInfoVO.getUserId())) {
					userAppInfoDAO.delete(userAppInfoVO);
					AppReceiverVO appReceiverVO = appReceiverDAO.get(userAppInfoVO.getAppInfoId());
					appReceiverVO.setStatus(null);
					appReceiverDAO.saveOrUpdate(appReceiverVO);
				}
			}

			// 找出所有app转移求助信息
			List<AppReceiverVO> appReceiveVOList = appReceiverDAO.queryForObject("from AppReceiverVO where status != ? or status is null", new String[] {AppReceiverVO.STATUS_PROCESSING});

			// 找出在线用户24小时app求助信息分配数量
			String hql = "select t.agentId, count(t.pid) as userCount from AppReceiverHistVO t, UserStatusVO s where t.agentId = s.userId and s.status = ? and t.createTime between ? and ? group by t.agentId order by userCount";
			Date sDt = DateUtil.parseDt(DateUtil.format(DateUtil.getToday(), "yyyy-MM-dd"), "yyyy-MM-dd");
			Date eDt = new Date();
			List<Object[]> countList = appReceiverHistDAO.queryFromObject(hql,
					new Object[] { UserStatusVO.STATUS_1, sDt, eDt });
			
			Map<String, Long> countMap = new HashMap<String, Long>();
			for (Object[] objs : countList) {
				countMap.put(String.valueOf(objs[0]), (Long) objs[1]);
			}
			
			List<CountBean> cbeanList = new ArrayList<CountBean>();
			for (UserStatusVO vo : userStatusList) {
				CountBean bean = new CountBean();
				bean.setId(vo.getUserId());
				if(countMap.containsKey(vo.getUserId())) {
					bean.setCount(countMap.get(vo.getUserId()));
				} else {
					bean.setCount(Long.valueOf("0"));
				}
				cbeanList.add(bean);
			}

			Collections.sort(cbeanList, new Comparator<CountBean>() {
				@Override
				public int compare(CountBean o1, CountBean o2) {
					return o1.getCount().compareTo(o2.getCount());
				}
			});

			Date createDt = DateUtil.getNowDate();
			for (int i = 0; i < appReceiveVOList.size(); i++) {
				AppReceiverVO appReceiverVO = appReceiveVOList.get(i);
				CountBean bean = null;
				if (i  >= cbeanList.size()) {
					bean = cbeanList.get(i%cbeanList.size());
				} else {
					bean = cbeanList.get(i);
				}

				UserAppInfoVO uaiVO = new UserAppInfoVO();
				uaiVO.setAppInfoId(appReceiverVO.getPid());
				uaiVO.setUserId(bean.getId());
				uaiVO.setCreateTime(createDt);
				userAppInfoDAO.saveOrUpdate(uaiVO);
				
				appReceiverVO.setStatus(AppReceiverVO.STATUS_PROCESSING);
				appReceiverDAO.saveOrUpdate(appReceiverVO);
			}
		}
	}
}
