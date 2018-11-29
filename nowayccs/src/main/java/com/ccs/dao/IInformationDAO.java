package com.ccs.dao;

import java.util.Date;
import java.util.List;

import com.ccs.bean.AffairInfoSearchBean;
import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.LifeInfoSearchBean;
import com.ccs.util.PageInfo;
import com.ccs.vo.HelpCountByPhoneBean;
import com.ccs.vo.InformationVO;

public interface IInformationDAO {

	void saveOrUpate(final InformationVO vo);
	
	void save(final InformationVO vo);
	
	void delete(final InformationVO vo);
	
	InformationVO findById(final String infoId);
	
	List<InformationVO> findByHelpTel(final String helpTel, PageInfo pageInfo);
	
	int getTotalCount(final String helpTel);
	
	List<InformationVO> findByCreatorAndStatus(final String userId, final String status, final String helpType, PageInfo pageInfo);
	
	int getTotalCount(final String userId, final String status, String helpType);
	
	List<InformationVO> findByAffairAcceptorAndStatus(final String userId, final String status, final String helpType, PageInfo pageInfo);
	
	int getTotalCountByAffairAcceptorAndStatus(final String userId, final String status, String helpType);
	
	List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo);
	
	int getTotalCountByDeliverer(String deliverer, String helpType);
	
	List<InformationVO> findByParams(InfoSearchBean bean, PageInfo pageInfo);
	
	int getTotalCountByParams(InfoSearchBean bean);
	
	int getInfoCount(final String helpType);
	
	List<InformationVO> findLifeInfoByParams(LifeInfoSearchBean bean, PageInfo pageInfo);
	
	int getLifeCountByParams(LifeInfoSearchBean bean);
	
	List<InformationVO> findAffairInfoByParams(AffairInfoSearchBean bean, PageInfo pageInfo);
	
	int getAffairCountByParams(AffairInfoSearchBean bean);
	
	List<HelpCountByPhoneBean> getHelpCountByPhone(final Date startDt, final Date endDt, final PageInfo pageInfo);
	public int getHelpCountByPhoneCount(final Date startDt, final Date endDt);
	
	List<InformationVO> findByCallId(final String callId);
	
	List<InformationVO> findInfo(final String creator, final String helpType, final String status);
}
