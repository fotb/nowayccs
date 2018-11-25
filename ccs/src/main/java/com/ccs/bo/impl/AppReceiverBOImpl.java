package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.util.DateUtil;
import com.ccs.vo.AppInfoVO;
import com.ccs.vo.AppReceiverVO;
import com.ccs.vo.InformationVO;
import com.ccs.web.domain.AppInfoBean;
@Service("appReceiverBO")
@Transactional
public class AppReceiverBOImpl implements IAppReceiverBO {

	@Autowired
	private IBaseDAO<AppReceiverVO> appReceiverDAO;
	
	@Autowired
	private IBaseDAO<AppInfoVO> appInfoDAO;
	
	@Autowired
	private IInformationDAO informationDAO;
	
	@Override
	public void create(AppReceiverVO vo) throws Exception {
		vo.setCreateTime(new Date());
		appReceiverDAO.save(vo);
	}


	@Override
	public List<AppReceiverVO> findByIds(List<String> ids) throws Exception {
		String hql = "from AppReceiverVO where pid in (";
		for(int i = 0; i < ids.size(); i++) {
			if(i < ids.size() - 1) {
				hql = hql + "?,";
			} else {
				hql = hql + "?";
			}
		}
		hql = hql + ")";
		
		return appReceiverDAO.queryForObject(hql, ids.toArray());
	}


	@Override
	public AppReceiverVO findById(String id) throws Exception {
		return appReceiverDAO.get(id);
	}


	@Override
	public void addManual(List<AppInfoBean> list) throws Exception {
		final String hql = "from AppReceiverVO where orderNumber = ?";
		for (AppInfoBean appInfoBean : list) {
			List<AppReceiverVO> appReceiverVOList = appReceiverDAO.queryForObject(hql, new Object[] {appInfoBean.getOrderNumber()});
			if(appReceiverVOList.isEmpty()) {
				AppReceiverVO vo = createAppReceVO(appInfoBean);
				appReceiverDAO.save(vo);
			} else {
				AppReceiverVO vo = createFromAppReceVO(appReceiverVOList.get(0), appInfoBean);
				appReceiverDAO.update(vo);
			}
			
		}
	}


	@Override
	public void addInfo(List<AppInfoBean> list) throws Exception {
		final String hql = "from AppInfoVO t where t.informationId = ?";
		for (AppInfoBean appInfoBean : list) {
			List<InformationVO> inforVOList = informationDAO.findByCallId(appInfoBean.getOrderNumber());
			if(inforVOList.isEmpty()) {
				informationDAO.save(createInformationVO(appInfoBean));
			} else {
				informationDAO.saveOrUpate(createFromInformationVO(inforVOList.get(0), appInfoBean));
			}
			
			
			List<AppInfoVO> appInfoVOList = appInfoDAO.queryForObject(hql, new Object[] {appInfoBean.getOrderNumber()});
			if(appInfoVOList.isEmpty()) {
				appInfoDAO.saveOrUpdate(createAppInfoVO(appInfoBean));
			} else {
				
				appInfoDAO.saveOrUpdate(createFromAppInfoVO(appInfoVOList.get(0), appInfoBean));
			}
		}
	}

	
	private AppReceiverVO createAppReceVO(AppInfoBean bean) {
		AppReceiverVO vo = new AppReceiverVO();
    	vo.setHelpName(bean.getHelpName());
    	vo.setHelpMode(bean.getHelpMode());
    	vo.setHelpTel(bean.getHelpTel());
    	vo.setHelpAddr(bean.getHelpAddr());
    	vo.setHelpContent(bean.getHelpContent());
    	vo.setHelpType(bean.getHelpType());
    	vo.setHelpArea(bean.getHelpArea());
    	vo.setHelpGroup(bean.getHelpGroup());
    	vo.setCreateTime(DateUtil.parse(bean.getOrderCreateTime(), "yyyy-MM-dd HH:mm:ss"));
    	vo.setOrderNumber(bean.getOrderNumber());
		return vo;
	}
	
	
	private AppReceiverVO createFromAppReceVO(AppReceiverVO vo, AppInfoBean bean) {
    	vo.setHelpName(bean.getHelpName());
    	vo.setHelpMode(bean.getHelpMode());
    	vo.setHelpTel(bean.getHelpTel());
    	vo.setHelpAddr(bean.getHelpAddr());
    	vo.setHelpContent(bean.getHelpContent());
    	vo.setHelpType(bean.getHelpType());
    	vo.setHelpArea(bean.getHelpArea());
    	vo.setHelpGroup(bean.getHelpGroup());
    	vo.setCreateTime(DateUtil.parse(bean.getOrderCreateTime(), "yyyy-MM-dd HH:mm:ss"));
    	vo.setOrderNumber(bean.getOrderNumber());
		return vo;
	}
	
	private InformationVO createInformationVO(AppInfoBean bean) {
		InformationVO vo = new InformationVO();
		vo.setCallId(bean.getOrderNumber());
		vo.setHelpName(bean.getHelpName());
		vo.setHelpMode(bean.getHelpMode());
		vo.setHelpTel(bean.getHelpTel());
		vo.setHelpAddr(bean.getHelpAddr());
		vo.setHelpContent(bean.getHelpContent());
		vo.setHelpType(bean.getHelpType());
		vo.setHelpArea(bean.getHelpArea());
		vo.setHelpGroup(bean.getHelpGroup());
		vo.setCreator("1");
		vo.setCreateTime(DateUtil.parse(bean.getOrderCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setFinishTime(DateUtil.parse(bean.getOrderOverTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setStatus(bean.getOrderStatus());
		vo.setCanceler("1");
		vo.setCancelTime(DateUtil.parse(bean.getOrderOverTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setDeliverer("1");
		vo.setDeliverMode("2"); //App 方式
		vo.setDeliverTime(DateUtil.parse(bean.getOrderDoTime(), "yyyy-MM-dd HH:mm:ss"));
		return vo;
	}
	
	private InformationVO createFromInformationVO(InformationVO vo, AppInfoBean bean) {
		vo.setCallId(bean.getOrderNumber());
		vo.setHelpName(bean.getHelpName());
		vo.setHelpMode(bean.getHelpMode());
		vo.setHelpTel(bean.getHelpTel());
		vo.setHelpAddr(bean.getHelpAddr());
		vo.setHelpContent(bean.getHelpContent());
		vo.setHelpType(bean.getHelpType());
		vo.setHelpArea(bean.getHelpArea());
		vo.setHelpGroup(bean.getHelpGroup());
		vo.setCreator("1");
		vo.setCreateTime(DateUtil.parse(bean.getOrderCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setFinishTime(DateUtil.parse(bean.getOrderOverTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setStatus(bean.getOrderStatus());
		vo.setCanceler("1");
		vo.setCancelTime(DateUtil.parse(bean.getOrderOverTime(), "yyyy-MM-dd HH:mm:ss"));
		vo.setDeliverer("1");
		vo.setDeliverMode("2"); //App 方式
		vo.setDeliverTime(DateUtil.parse(bean.getOrderDoTime(), "yyyy-MM-dd HH:mm:ss"));
		return vo;
	}
	
	private AppInfoVO createAppInfoVO(AppInfoBean bean) {
		AppInfoVO vo = new AppInfoVO();
		vo.setInformationId(bean.getOrderNumber());
		vo.setOrdersCommentContent(bean.getOrdersCommentContent());
		vo.setOrdersCommentNum(bean.getOrdersCommentNum());
		vo.setOrderServersCheck(bean.getOrdersServersCheck());
		vo.setOrdersMoney(bean.getOrdersMoney());
		vo.setOrdersServersPhone(bean.getOrdersServersPhone());
		vo.setOrdersServersServiceType(bean.getOrdersServersServiceType());
		vo.setOrdersServiesName(bean.getOrdersServersName());
		vo.setOrdersType(bean.getOrdersType());
		return vo;
	}
	
	private AppInfoVO createFromAppInfoVO(AppInfoVO vo, AppInfoBean bean) {
		vo.setOrdersCommentContent(bean.getOrdersCommentContent());
		vo.setOrdersCommentNum(bean.getOrdersCommentNum());
		vo.setOrderServersCheck(bean.getOrdersServersCheck());
		vo.setOrdersMoney(bean.getOrdersMoney());
		vo.setOrdersServersPhone(bean.getOrdersServersPhone());
		vo.setOrdersServersServiceType(bean.getOrdersServersServiceType());
		vo.setOrdersServiesName(bean.getOrdersServersName());
		vo.setOrdersType(bean.getOrdersType());
		return vo;
	}
}
