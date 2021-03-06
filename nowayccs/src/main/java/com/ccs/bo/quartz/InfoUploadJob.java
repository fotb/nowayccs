package com.ccs.bo.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.dao.IBaseDAO;
import com.ccs.dao.IInformationDAO;
import com.ccs.dao.ILifeInformationDAO;
import com.ccs.dao.IUserDAO;
import com.ccs.services.client.ZhServiceConstants;
import com.ccs.services.client.ZhServiceSoapProxy;
import com.ccs.services.vo.AjUploadVO;
import com.ccs.services.vo.CallTelUploadVO;
import com.ccs.services.vo.UploadUtils;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.StringUtil;
import com.ccs.util.XmlUtil;
import com.ccs.vo.BaseEntity;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.UpInfoStatusHistVO;
import com.ccs.vo.UpInfoStatusVO;
import com.ccs.vo.UserVO;

@Component
public class InfoUploadJob implements Job {

	private static final Logger LOG = Logger.getLogger(InfoUploadJob.class);

	@Autowired
	private IBaseDAO<UpInfoStatusVO> upInfoStatusDAO;
	
	@Autowired
	private IBaseDAO<UpInfoStatusHistVO> upInfoStatusHistDAO;

	@Autowired
	private IInformationDAO informationDAO;

	@Autowired
	private ILifeInformationDAO lifeInformationDAO;

	@Autowired
	private IUserDAO userDAO;
	
	public UpInfoStatusHistVO createUishVO(UpInfoStatusVO uisVO) {
		UpInfoStatusHistVO uishVO = new UpInfoStatusHistVO();
		uishVO.setInfomationId(uisVO.getInfomationId());
		uishVO.setUpStatus(uisVO.getUpStatus());
		uishVO.setUpLoadDt(uisVO.getUpLoadDt());
		uishVO.setResult(uisVO.getResult());
		uishVO.setRemark(uisVO.getRemark());
		uishVO.setCreateTime(new Date());
		uishVO.setDeleteFlag(BaseEntity.DELETE_FLAG_NO);
		uishVO.setLastHandler(uisVO.getLastHandler());
		uishVO.setPid(uisVO.getPid());
		uishVO.setUpdateDT(new Date());
		
		return uishVO;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// query all unupload data
			String hql = "from UpInfoStatusVO where upStatus = ?";
			List<UpInfoStatusVO> uisVOList = upInfoStatusDAO.queryForObject(hql,
					new String[] { UpInfoStatusVO.UPLOAD_STATUS_NO });

			for (UpInfoStatusVO upInfoStatusVO : uisVOList) {
				String infoId = upInfoStatusVO.getInfomationId();
				InformationVO infoVO = informationDAO.findById(infoId);

				AjUploadVO ajuVO = new AjUploadVO();
				ajuVO.setOriginalid(infoId);
				ajuVO.setQuerytype(UploadUtils.getQuerytype(infoVO.getHelpMode()));
				ajuVO.setCalltel(infoVO.getHelpTel());
				ajuVO.setName(infoVO.getHelpName());
				ajuVO.setSex("");
				ajuVO.setOtherlink("");
				ajuVO.setTransactiontype(UploadUtils.getTransType(infoVO.getHelpType()));
				ajuVO.setTransactionlclass("01");
				ajuVO.setTransactionsclass("0101");
				ajuVO.setHelptime(DateUtil.format(infoVO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				ajuVO.setHelpcontent(infoVO.getHelpContent());
				ajuVO.setHelptitle("");

				// 如果是生活类服务器，且已经结案，则记录Reply信息
				if (Constants.INFOMATION_HELPTYPE_LIFE.equals(infoVO.getHelpType())
						&& Constants.SYS_INFOMATION_STATES_YJA.equals(infoVO.getStatus())) {
					LifeInformationVO lInfoVO = lifeInformationDAO.findByInfoId(infoVO.getInfoId());
					if( null == lInfoVO) {
						ajuVO.setReplycontent("");
						ajuVO.setReplytime("");
					} else {
						ajuVO.setReplycontent(StringUtil.isNull(lInfoVO.getCallResult()) ? "" : lInfoVO.getCallResult());
						ajuVO.setReplytime(DateUtil.format(lInfoVO.getCallTime(), "yyyy-MM-dd HH:mm:ss"));
					}
				} else {
					ajuVO.setReplycontent("");
					ajuVO.setReplytime("");
				}

				ajuVO.setHelpstate(UploadUtils.getHelpState(infoVO.getStatus()));
				ajuVO.setEntername("");
				ajuVO.setServqual("");
				ajuVO.setEnterlevel("");
				ajuVO.setHostdepart("");
				ajuVO.setHelpbuild("1");

				UserVO userVO = userDAO.findById(infoVO.getCreator());
				ajuVO.setSeatname(userVO.getLoginName());
				ajuVO.setSeatip(userVO.getLoginName());

				ZhServiceSoapProxy proxy = new ZhServiceSoapProxy();
				// String login = proxy.login(ZhServiceConstants.USER,
				// ZhServiceConstants.PW);
				// if(ZhServiceConstants.LOGIN_STATUS_0.equals(login)) {

				if (Constants.SYS_INFOMATION_STATES_DB.equals(infoVO.getStatus())) {
					CallTelUploadVO ctuVO = new CallTelUploadVO();
					ctuVO.setSeatname(userVO.getLoginName());
					ctuVO.setSeatip(userVO.getLoginName());
					ctuVO.setCalltel(infoVO.getHelpTel());
					ctuVO.setDealtime(DateUtil.format(infoVO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					ctuVO.setDealstatus("1");

					String xml = XmlUtil.toXml(ctuVO);
					LOG.warn("start to upload calltel with infoId = " + infoVO.getInfoId());
					String result = proxy.calltel_Upload(ZhServiceConstants.USER, ZhServiceConstants.PW,
							"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml);
					if (ZhServiceConstants.RETURN_CODE_1000.equals(result)) {
						LOG.warn("Success to upload calltel with infoId = " + infoVO.getInfoId());
					}
				}

				LOG.warn("start to upload info with infoId = " + infoVO.getInfoId());

				String xml = XmlUtil.toXml(ajuVO);
				String result = proxy.AJ_Upload(ZhServiceConstants.USER, ZhServiceConstants.PW,
						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml);
				if (ZhServiceConstants.RETURN_CODE_1000.equals(result)) {
					LOG.warn("Success to upload info with infoId = " + infoVO.getInfoId());
					upInfoStatusVO.setUpStatus(UpInfoStatusVO.UPLOAD_STATUS_YES);
					upInfoStatusVO.setResult(result);
					upInfoStatusHistDAO.save(createUishVO(upInfoStatusVO));
					upInfoStatusDAO.delete(upInfoStatusVO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Upload Aj Fail: " + e.getMessage());
		}
		
	}

}
