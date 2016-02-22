package com.ccs.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IDictBO;
import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.bo.IUserBO;
import com.ccs.dao.IAreaDAO;
import com.ccs.dao.IAreaSubDAO;
import com.ccs.dao.IBaseDAO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.EasyUiTree;
import com.ccs.util.StringUtil;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.BaseEntity;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LPSRowBean;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerInfoListBean;
import com.ccs.web.domain.PowerStaffDomain;
import com.ccs.web.domain.PowerStaffListBean;
import com.ccs.web.domain.PowerStaffReportBean;

@Service("lpsBO")
public class LightPowerStaffBOImpl implements ILightPowerStaffBO {

	@Autowired
	private IBaseDAO<PowerStaffVO> lpsDAO;

	@Autowired
	private IBaseDAO<PowerStaffAreaVO> lpsaDAO;

	@Autowired
	private IBaseDAO<PowerInformationVO> piDAO;

	@Autowired
	private IAreaDAO areaDAO;

	@Autowired
	private IAreaSubDAO areaSubDAO;
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;

	@Override
	@Transactional
	public void saveLPS(PowerStaffDomain psDomain, UserVO userVO) throws Exception {
		PowerStaffVO psVO = new PowerStaffVO();
		psVO.setCreateTime(new Date());
		psVO.setName(psDomain.getName());
		psVO.setPhone(psDomain.getPhone());
		psVO.setRemark(psDomain.getRemark());
		psVO.setCategory(psDomain.getCategory());
		psVO.setLastHandler(userVO.getUserId());
		lpsDAO.save(psVO);

		if (PowerStaffVO.CATEGORY_4.equals(psDomain.getCategory())) {
			String[] areaId = psDomain.getAreaSubId().split(",");
			for (String id : areaId) {
				PowerStaffAreaVO psaVO = new PowerStaffAreaVO();
				psaVO.setAreaSubId(id);
				psaVO.setCreateTime(new Date());
				psaVO.setStaffId(psVO.getPid());
				psaVO.setLastHandler(userVO.getUserId());
				lpsaDAO.save(psaVO);
			}
		} else {
			PowerStaffAreaVO psaVO = new PowerStaffAreaVO();
			psaVO.setAreaSubId(psDomain.getAreaId());
			psaVO.setCreateTime(new Date());
			psaVO.setStaffId(psVO.getPid());
			psaVO.setLastHandler(userVO.getUserId());
			lpsaDAO.save(psaVO);
		}
	}

	@Override
	public LightPowerStaffTreeBean buildLPSTree() throws Exception {

		List<AreaVO> areaList = areaDAO.findAll();
		Map<String, AreaVO> areaMap = new HashMap<String, AreaVO>();
		for (AreaVO areaVO : areaList) {
			areaMap.put(areaVO.getAreaId(), areaVO);
		}

		List<AreaSubVO> areaSubList = areaSubDAO.findAll();
		Map<String, AreaSubVO> areaSubMap = new HashMap<String, AreaSubVO>();
		for (AreaSubVO areaSubVO : areaSubList) {
			areaSubMap.put(areaSubVO.getAreaSubId(), areaSubVO);
		}

		List<PowerStaffVO> list = lpsDAO.getAll();
		Map<String, PowerStaffVO> psVOMap = new HashMap<String, PowerStaffVO>();
		for (PowerStaffVO powerStaffVO : list) {
			psVOMap.put(powerStaffVO.getPid(), powerStaffVO);
		}

		Map<String, AreaVO> lpsAreaMap = new HashMap<String, AreaVO>();
		Map<String, AreaSubVO> lpsAreaSubMap = new HashMap<String, AreaSubVO>();

		List<PowerStaffAreaVO> psaList = lpsaDAO.getAll();
		LightPowerStaffTreeBean lpsTreeBean = new LightPowerStaffTreeBean();
		List<LPSRowBean> lpsRowBeanList = new ArrayList<LPSRowBean>();
		for (PowerStaffAreaVO psaVO : psaList) {
			PowerStaffVO psVO = psVOMap.get(psaVO.getStaffId());
			LPSRowBean lpsRowBean = new LPSRowBean();
			lpsRowBean.setId(psVO.getPid());
			lpsRowBean.setName(psVO.getName());
			lpsRowBean.setPhone(psVO.getPhone());
			lpsRowBean.setRemark(psVO.getRemark());
			lpsRowBean.setCategory(psVO.getCategory());
			// lpsRowBean.setIconCls(LPSRowBean.ICON_OK);
			lpsRowBean.set_parentId(psaVO.getAreaSubId());
			lpsRowBeanList.add(lpsRowBean);

			if (PowerStaffVO.CATEGORY_4.equals(psVO.getCategory())) {
				if (!lpsAreaSubMap.containsKey(psaVO.getAreaSubId())) {
					lpsAreaSubMap.put(psaVO.getAreaSubId(), areaSubMap.get(psaVO.getAreaSubId()));
				}

				String psAreaId = areaSubMap.get(psaVO.getAreaSubId()).getAreaId();
				if (!lpsAreaMap.containsKey(psAreaId)) {
					lpsAreaMap.put(psAreaId, areaMap.get(psAreaId));
				}
			} else {
				if (!lpsAreaMap.containsKey(psaVO.getAreaSubId())) {
					lpsAreaMap.put(psaVO.getAreaSubId(), areaMap.get(psaVO.getAreaSubId()));
				}
			}

		}

		for (AreaVO areaVO : lpsAreaMap.values()) {
			LPSRowBean lpsRowBean = new LPSRowBean();
			lpsRowBean.setId(areaVO.getAreaId());
			lpsRowBean.setName(areaVO.getName());
			// lpsRowBean.setIconCls(LPSRowBean.ICON_OK);
			lpsRowBeanList.add(lpsRowBean);
		}

		for (AreaSubVO areaSubVO : lpsAreaSubMap.values()) {
			LPSRowBean lpsRowBean = new LPSRowBean();
			lpsRowBean.setId(areaSubVO.getAreaSubId());
			lpsRowBean.setName(areaSubVO.getName());
			// lpsRowBean.setIconCls(LPSRowBean.ICON_OK);
			lpsRowBean.set_parentId(areaSubVO.getAreaId());
			lpsRowBeanList.add(lpsRowBean);
		}
		lpsTreeBean.setRows(lpsRowBeanList);
		lpsTreeBean.setTotal(String.valueOf(lpsRowBeanList.size()));
		return lpsTreeBean;
	}

	@Override
	public void deleteLPS(String id, UserVO userVO) throws Exception {
		PowerStaffVO psVO = lpsDAO.get(id);
		psVO.setDeleteFlag(PowerStaffVO.DELETE_FLAG_YES);
		psVO.setUpdateDT(new Date());
		psVO.setLastHandler(userVO.getUserId());
		lpsDAO.update(psVO);
		List<PowerStaffAreaVO> list = lpsaDAO.queryForObject("from PowerStaffAreaVO where staffId = ?",
				new String[] { id });
		for (PowerStaffAreaVO powerStaffAreaVO : list) {
			powerStaffAreaVO.setDeleteFlag(PowerStaffAreaVO.DELETE_FLAG_YES);
			lpsaDAO.update(powerStaffAreaVO);
		}
	}

	@Override
	public PowerStaffVO findPowerStaffById(String id) throws Exception {
		return lpsDAO.get(id);
	}

	@Override
	public void updatePowerStaff(PowerStaffVO psVO) throws Exception {
		lpsDAO.update(psVO);
	}

	@Override
	public List<PowerStaffAreaVO> findPSAById(String id) throws Exception {
		return lpsaDAO.queryForObject("from PowerStaffAreaVO where staffId = ?", new String[] { id });
	}

	@Override
	public List<PowerStaffReportBean> powerStaffReport(String areaId, String areaSubId, String startDt, String endDt)
			throws Exception {

		List<PowerStaffReportBean> psrBeanList = new ArrayList<PowerStaffReportBean>();

		String hql = "from PowerStaffAreaVO t where ";
		List<Object> params = new ArrayList<Object>();
		if (StringUtil.isNull(areaSubId)) {
			List<AreaSubVO> asVOList = new ArrayList<AreaSubVO>();
			if (StringUtil.isNull(areaId)) {
				asVOList = areaSubDAO.findAll();
			} else {
				asVOList = areaSubDAO.findByAreaId(areaId);
			}
			if (asVOList.isEmpty()) {
				hql += "t.areaSubId in (?)";
				params.add("");
			} else {
				hql += "t.areaSubId in (";
				int i = 1;
				for (AreaSubVO areaSubVO : asVOList) {
					if (i < asVOList.size()) {
						hql += "?,";
					} else {
						hql += "?";
					}
					params.add(areaSubVO.getAreaSubId());
					i++;
				}
				hql += ")";
			}

		} else {
			hql += "t.areaSubId = ?";
			params.add(areaSubId);
		}
		hql += " and t.deleteFlag = ?";
		params.add(BaseEntity.DELETE_FLAG_NO);
		List<PowerStaffAreaVO> psaVOList = lpsaDAO.queryForObject(hql, params.toArray());
		if (psaVOList.isEmpty()) {
			return psrBeanList;
		}
		List<String> staffIdList = new ArrayList<String>();
		Map<String, String> psaMap = new HashMap<String, String>();
		for (PowerStaffAreaVO psaVO : psaVOList) {
			staffIdList.add(String.valueOf(psaVO.getStaffId()));
			psaMap.put(psaVO.getStaffId(), psaVO.getAreaSubId());
		}

		String hql1 = "from PowerStaffVO t where t.pid in (";
		params.clear();
		int i = 1;
		for (String staffId : staffIdList) {
			if (i < staffIdList.size()) {
				hql1 += "?,";
			} else {
				hql1 += "?)";
			}
			params.add(staffId);
			i++;
		}
		List<PowerStaffVO> psVOList = lpsDAO.queryForObject(hql1, params.toArray());

		String hql2 = "select t.powerStaffId, count(t.pid) from PowerInformationVO t where t.createTime between ? and ? group by t.powerStaffId";
		Date sDt = StringUtil.isNull(startDt) ? DateUtil.addMonth(DateUtil.getToday(), -1)
				: DateUtil.parse(startDt, "yyyy-MM-dd");
		Date eDt = StringUtil.isNull(endDt) ? new Date() : DateUtil.parseDt(endDt + " 23:59:59", "yyyy-MM-dd hh:mm:ss");

		List<Object[]> countList = piDAO.queryFromObject(hql2, new Object[] { sDt, eDt });
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for (Object[] objs : countList) {
			countMap.put(String.valueOf(objs[0]), ((Long) objs[1]).intValue());
		}

		Date tDay = DateUtil.parseDt(DateUtil.format(DateUtil.getToday(), "yyyy-MM-dd"), "yyyy-MM-dd");
		Date teDay = new Date();
		List<Object[]> todayCountList = piDAO.queryFromObject(hql2, new Object[] { tDay, teDay });
		Map<String, Integer> todayCountMap = new HashMap<String, Integer>();
		for (Object[] objs : todayCountList) {
			todayCountMap.put(String.valueOf(objs[0]), ((Long) objs[1]).intValue());
		}

		List<AreaVO> aVOList = areaDAO.findAll();
		Map<String, String> areaMap = new HashMap<String, String>();
		for (AreaVO areaVO : aVOList) {
			areaMap.put(areaVO.getAreaId(), areaVO.getName());
		}

		List<AreaSubVO> asVOList = areaSubDAO.findAll();
		Map<String, String> areaSubMap = new HashMap<String, String>();
		Map<String, String> areaSubNameMap = new HashMap<String, String>();
		for (AreaSubVO areaSubVO : asVOList) {
			areaSubMap.put(areaSubVO.getAreaSubId(), areaSubVO.getName());
			areaSubNameMap.put(areaSubVO.getAreaSubId(), areaMap.get(areaSubVO.getAreaId()));
		}

		for (PowerStaffVO psVO : psVOList) {
			PowerStaffReportBean bean = new PowerStaffReportBean();
			bean.setStaffId(psVO.getPid());
			bean.setName(psVO.getName());
			bean.setPhone(psVO.getPhone());
			bean.setCount(countMap.get(psVO.getPid()) == null ? 0 : countMap.get(psVO.getPid()));
			bean.setTodayCount(todayCountMap.get(psVO.getPid()) == null ? 0 : todayCountMap.get(psVO.getPid()));
			bean.setArea(areaSubNameMap.get(psaMap.get(psVO.getPid())));
			bean.setAreaSub(areaSubMap.get(psaMap.get(psVO.getPid())));
			psrBeanList.add(bean);
		}

		return psrBeanList;
	}

	@Override
	public List<PowerStaffVO> findAll() throws Exception {
		return lpsDAO.getAll();
	}

	@Override
	@Transactional
	public void saveOrUpdate(List<PowerStaffAreaVO> psaVOList) throws Exception {
		for (PowerStaffAreaVO psaVO : psaVOList) {
			lpsaDAO.saveOrUpdate(psaVO);
		}
	}

	@Override
	public List<PowerStaffListBean> buildList(String areaId, int page, int rows) throws Exception {
		List<PowerStaffListBean> pslBeanList = new ArrayList<PowerStaffListBean>();

		List<AreaSubVO> asVOList = new ArrayList<AreaSubVO>();
		if (StringUtil.isNull(areaId)) {
			asVOList = areaSubDAO.findAll();
		} else {
			asVOList = areaSubDAO.findByAreaId(areaId);
		}

		Map<String, String> areaSubMap = new HashMap<String, String>();

		List<Object> params = new ArrayList<Object>();
		for (AreaSubVO areaSubVO : asVOList) {
			areaSubMap.put(areaSubVO.getAreaSubId(), areaSubVO.getName());
		}
 
		for (AreaSubVO areaSubVO : asVOList) {
			params.add(areaSubVO.getAreaSubId());
		}
		
		DetachedCriteria psaCriteria = DetachedCriteria.forClass(PowerStaffAreaVO.class);
		psaCriteria.add(Restrictions.in("areaSubId", params));
		psaCriteria.add(Restrictions.eq("deleteFlag", BaseEntity.DELETE_FLAG_NO));
		
		
		List<PowerStaffAreaVO> psaVOList = lpsaDAO.findByDetachedCriteria(psaCriteria, page, rows);
		
		List<PowerStaffVO> psVOList = lpsDAO.getAllWithDeleted();
		Map<String, PowerStaffVO> psMap = new HashMap<String, PowerStaffVO>();
		for (PowerStaffVO powerStaffVO : psVOList) {
			psMap.put(powerStaffVO.getPid(), powerStaffVO);
		}
		
		for (PowerStaffAreaVO psaVO : psaVOList) {
			PowerStaffListBean bean = new PowerStaffListBean();
			PowerStaffVO psVO = psMap.get(psaVO.getStaffId());
			bean.setName(psVO.getName());
			bean.setPhone(psVO.getPhone());
			bean.setCategory(psVO.getCategory());
			bean.setAreaSubName(areaSubMap.get(psaVO.getAreaSubId()));
			bean.setDeleteFlag(psVO.getDeleteFlag());
			bean.setPid(psVO.getPid());
			pslBeanList.add(bean);
		}
		
		return pslBeanList;
	}

	@Override
	public int countBuildList(String areaId) throws Exception {
		List<AreaSubVO> asVOList = new ArrayList<AreaSubVO>();
		if (StringUtil.isNull(areaId)) {
			asVOList = areaSubDAO.findAll();
		} else {
			asVOList = areaSubDAO.findByAreaId(areaId);
		}

		List<Object> params = new ArrayList<Object>();
		for (AreaSubVO areaSubVO : asVOList) {
			params.add(areaSubVO.getAreaSubId());
		}
		
		DetachedCriteria psaCriteria = DetachedCriteria.forClass(PowerStaffAreaVO.class);
		psaCriteria.add(Restrictions.in("areaSubId", params));
		psaCriteria.add(Restrictions.eq("deleteFlag", BaseEntity.DELETE_FLAG_NO));
		
		return lpsaDAO.getRowCountByDetachedCriteria(psaCriteria);
	}

	@Override
	public List<EasyUiTree> buildAreaTree() throws Exception {


		List<AreaSubVO> asVOList = areaSubDAO.findAll();
		Map<String, List<EasyUiTree>> areaSubMap = new HashMap<String, List<EasyUiTree>>();
		for (AreaSubVO areaSubVO : asVOList) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(areaSubVO.getAreaSubId());
			tree.setText(areaSubVO.getName());
			List<EasyUiTree> easyUiTreeList = areaSubMap.get(areaSubVO.getAreaId());
			if(null == easyUiTreeList ||  easyUiTreeList.isEmpty()) {
				easyUiTreeList = new ArrayList<EasyUiTree>();
			} 
			easyUiTreeList.add(tree);
			areaSubMap.put(areaSubVO.getAreaId(), easyUiTreeList);
		}
		
		List<AreaVO> aVOList = areaDAO.findAll();
		List<EasyUiTree> list = new ArrayList<EasyUiTree>();
		for (AreaVO areaVO : aVOList) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(areaVO.getAreaId());
			tree.setText(areaVO.getName());
			tree.setChildren(areaSubMap.get(areaVO.getAreaId()));
			tree.setState("closed");
			list.add(tree);
		}
		
		return list;
	}

	
	
	@Override
	public List<PowerStaffVO> queryAllOrderByAreaSubId(String areaSubId, String psname, String psphone) throws Exception {
		
		String hql = "from PowerStaffVO where deleteFlag = ? and (name like ? or ? is null) and (phone like ? or ? is null)";
		List<PowerStaffVO> psVOList = lpsDAO.queryForObject(hql, new Object[]{BaseEntity.DELETE_FLAG_NO, "%" + psname + "%", psname, "%" + psphone + "%", psphone});
		
		List<Object[]> staffIds = lpsaDAO.queryFromObject("select staffId from PowerStaffAreaVO where deleteFlag = ? and areaSubId = ?", new Object[] {BaseEntity.DELETE_FLAG_NO, areaSubId});
		
		if(!staffIds.isEmpty()) {
			String hql1 = "from PowerStaffVO t where t.pid in (";
			List<Object> params = new ArrayList<Object>();
			for (int i = 0; i < staffIds.size(); i++) {
				if (i < staffIds.size() - 1) {
					hql1 += "?,";
				} else {
					hql1 += "?)";
				}
				params.add(staffIds.get(i));
			}
			List<PowerStaffVO> list = lpsDAO.queryForObject(hql1, params.toArray());
			
			for (PowerStaffVO powerStaffVO : list) {
				psVOList.remove(powerStaffVO);
			}
			
			
			psVOList.addAll(0, list);
		}
		return psVOList;
	}

	@Override
	public int countPSByAreaSubId(String areaSubId) throws Exception {
		List<Object[]> staffIds = lpsaDAO.queryFromObject("select staffId from PowerStaffAreaVO where deleteFlag = ? and areaSubId = ?", new Object[] {BaseEntity.DELETE_FLAG_NO, areaSubId});
		return staffIds.size();
	}

	@Override
	@Transactional
	public void associateSave(UserVO user, String areaSubId, String[] staffIds) throws Exception {
		
		List<PowerStaffAreaVO> psaVOList = lpsaDAO.queryForObject("from PowerStaffAreaVO where deleteFlag = ? and areaSubId = ?", new Object[] {BaseEntity.DELETE_FLAG_NO, areaSubId});
		
		List<String> existIds = new ArrayList<String>();
		for(PowerStaffAreaVO vo : psaVOList) {
			existIds.add(vo.getStaffId());
		}
		
		if(null != staffIds) {
			for (String id : staffIds) {
				if(!existIds.contains(id)) {
					lpsaDAO.save(convertToPSVO(user.getUserId(), areaSubId, id, BaseEntity.DELETE_FLAG_NO));
				}
			}
		}	
		
		for(PowerStaffAreaVO vo : psaVOList) {
			if(null != staffIds) {
				List<String> staffIdList = Arrays.asList(staffIds);
				if(!staffIdList.contains(vo.getStaffId())) {
					vo.setDeleteFlag(BaseEntity.DELETE_FLAG_YES);
					lpsaDAO.update(vo);
				}
			} else {
				vo.setDeleteFlag(BaseEntity.DELETE_FLAG_YES);
				lpsaDAO.update(vo);
			}
		}
	}

	private PowerStaffAreaVO convertToPSVO(String userId, String areaSubId, String staffId, int delFlag) {
		Date date = new Date();
		PowerStaffAreaVO psaVO = new PowerStaffAreaVO();
		psaVO.setAreaSubId(areaSubId);
		psaVO.setStaffId(staffId);
		psaVO.setCreateTime(date);
		psaVO.setUpdateDT(date);
		psaVO.setDeleteFlag(delFlag);
		psaVO.setLastHandler(userId);
		return psaVO;
	}

	@Override
	public List<PowerInfoListBean> queryPowerInfo(String startDt, String endDt,
			int page, int rows) throws Exception {
		String sql = "select t.informationid, t.helpname, t.helptel, t.helpmode, t.helpaddr, t.helpcontent, "
				+ "t.creator, t.createtime,d.powerstaffid, p.name, p.phone, d.areasubid, s.name as areasubname, "
				+ "a.name as areaname from hj_information t, HJ_POWERINFORMATION d, HJ_POWERSTAFF P, hj_area_sub s, hj_area a "
				+ "where t.informationId = d.informationid and d.areasubid = s.areasubid and s.areaid = a.areaid "
				+ "and d.POWERSTAFFID = p.pid "
				+ "and t.createtime >= to_date(?, 'yyyy-mm-dd hh24:mi:ss') and t.createtime <= to_date(?, 'yyyy-mm-dd hh24:mi:ss') "
				+ "order by t.createtime desc";
		
		Date sDt = StringUtil.isNull(startDt) ? DateUtil.addMonth(DateUtil.getToday(), -1)
				: DateUtil.parse(startDt, "yyyy-MM-dd");
		Date eDt = StringUtil.isNull(endDt) ? new Date() : DateUtil.parseDt(endDt + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
		
		List<String> objs = new ArrayList<String>();
		objs.add(DateUtil.format(sDt, "yyyy-MM-dd HH:mm:ss"));
		objs.add(DateUtil.format(eDt, "yyyy-MM-dd HH:mm:ss"));
//		List<Type> types = new ArrayList<Type>();
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
		
		Type[] types = new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING};
		
		List<Object[]> list = piDAO.createSQLQuery(sql, objs.toArray(), types, page, rows, true);

		List<PowerInfoListBean> beanList = new ArrayList<PowerInfoListBean>();
		
		Map<String, String> qzfsMap = dictBO.getDict(Constants.DICT_DICTTYPE_QZFS);
		Map<String, UserVO> userMap = userBO.findAll();
		
		for (Object[] obj : list) {
			PowerInfoListBean bean = new PowerInfoListBean();
			bean.setHelpName(null ==obj[1] ? "" : obj[1].toString());
			bean.setHelpTel(null ==obj[2] ? "" : obj[2].toString());
			bean.setHelpMode(null ==obj[3] ? "" : qzfsMap.get(obj[3].toString()));
			bean.setHelpAddr(null ==obj[4] ? "" :obj[4].toString());
			bean.setHelpContent(null ==obj[5] ? "" : obj[5].toString());
			bean.setUserName(null ==obj[6] ? "" : userMap.get(obj[6].toString()).getUserName());
			bean.setCreateDt(null ==obj[7] ? "" : obj[7].toString());
			bean.setPname(null ==obj[9] ? "" : obj[9].toString());
			bean.setPphone(null ==obj[10] ? "" : obj[10].toString());
			bean.setArea(obj[13].toString() + "-" + obj[12].toString());
			beanList.add(bean);
		}
		
		return beanList;
	}

	
	@Override
	public List<PowerInfoListBean> queryPowerInfo(String startDt, String endDt) throws Exception {
		String sql = "select t.informationid, t.helpname, t.helptel, t.helpmode, t.helpaddr, t.helpcontent, "
				+ "t.creator, t.createtime,d.powerstaffid, p.name, p.phone, d.areasubid, s.name as areasubname, "
				+ "a.name as areaname from hj_information t, HJ_POWERINFORMATION d, HJ_POWERSTAFF P, hj_area_sub s, hj_area a "
				+ "where t.informationId = d.informationid and d.areasubid = s.areasubid and s.areaid = a.areaid "
				+ "and d.POWERSTAFFID = p.pid "
				+ "and t.createtime >= to_date(?, 'yyyy-mm-dd hh24:mi:ss') and t.createtime <= to_date(?, 'yyyy-mm-dd hh24:mi:ss') "
				+ "order by t.createtime desc";
		
		Date sDt = StringUtil.isNull(startDt) ? DateUtil.addMonth(DateUtil.getToday(), -1)
				: DateUtil.parse(startDt, "yyyy-MM-dd");
		Date eDt = StringUtil.isNull(endDt) ? new Date() : DateUtil.parseDt(endDt + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
		
		List<String> objs = new ArrayList<String>();
		objs.add(DateUtil.format(sDt, "yyyy-MM-dd HH:mm:ss"));
		objs.add(DateUtil.format(eDt, "yyyy-MM-dd HH:mm:ss"));
//		List<Type> types = new ArrayList<Type>();
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
		
		Type[] types = new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING};
		
		List<Object[]> list = piDAO.createSQLQuery(sql, objs.toArray(), types, 1, 10, false);

		List<PowerInfoListBean> beanList = new ArrayList<PowerInfoListBean>();
		
		Map<String, String> qzfsMap = dictBO.getDict(Constants.DICT_DICTTYPE_QZFS);
		Map<String, UserVO> userMap = userBO.findAll();
		
		for (Object[] obj : list) {
			PowerInfoListBean bean = new PowerInfoListBean();
			bean.setHelpName(null ==obj[1] ? "" : obj[1].toString());
			bean.setHelpTel(null ==obj[2] ? "" : obj[2].toString());
			bean.setHelpMode(null ==obj[3] ? "" : qzfsMap.get(obj[3].toString()));
			bean.setHelpAddr(null ==obj[4] ? "" :obj[4].toString());
			bean.setHelpContent(null ==obj[5] ? "" : obj[5].toString());
			bean.setUserName(null ==obj[6] ? "" : userMap.get(obj[6].toString()).getUserName());
			bean.setCreateDt(null ==obj[7] ? "" : obj[7].toString());
			bean.setPname(null ==obj[9] ? "" : obj[9].toString());
			bean.setPphone(null ==obj[10] ? "" : obj[10].toString());
			bean.setArea(obj[13].toString() + "-" + obj[12].toString());
			beanList.add(bean);
		}
		
		return beanList;
	}
	@Override
	public int queryPowerInfoCount(String startDt, String endDt) throws Exception {
		String sql = "select count(t.informationid) as total "
				+ " from hj_information t, HJ_POWERINFORMATION d, HJ_POWERSTAFF P, hj_area_sub s, hj_area a "
				+ "where t.informationId = d.informationid and d.areasubid = s.areasubid and s.areaid = a.areaid "
				+ "and d.POWERSTAFFID = p.pid "
				+ "and t.createtime >= to_date(?, 'yyyy-mm-dd hh24:mi:ss') and t.createtime <= to_date(?, 'yyyy-mm-dd hh24:mi:ss')";
		
		Date sDt = StringUtil.isNull(startDt) ? DateUtil.addMonth(DateUtil.getToday(), -1)
				: DateUtil.parse(startDt, "yyyy-MM-dd");
		Date eDt = StringUtil.isNull(endDt) ? new Date() : DateUtil.parseDt(endDt + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
		
		List<String> objs = new ArrayList<String>();
		objs.add(DateUtil.format(sDt, "yyyy-MM-dd HH:mm:ss"));
		objs.add(DateUtil.format(eDt, "yyyy-MM-dd HH:mm:ss"));
//		List<Type> types = new ArrayList<Type>();
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
//		types.add(StandardBasicTypes.DATE);
		
		Type[] types = new Type[]{StandardBasicTypes.STRING, StandardBasicTypes.STRING};
		
		List<?> list = piDAO.createSQLQuery(sql, objs.toArray(), types);
		return ((BigDecimal)list.get(0)).intValue();
	}

	@Override
	@Transactional
	public void fix() throws Exception {
		List<PowerInformationVO> piVOList = piDAO.getAll();
		for (PowerInformationVO piVO : piVOList) {
			String powerStaffId = piVO.getPowerStaffId();
			List<PowerStaffAreaVO> psVOList = lpsaDAO.queryForObject("from PowerStaffAreaVO where staffId = ?", new Object[]{powerStaffId});
			piVO.setAreaSubId(psVOList.get(0).getAreaSubId());
			piDAO.update(piVO);
		}
	}
	
}
