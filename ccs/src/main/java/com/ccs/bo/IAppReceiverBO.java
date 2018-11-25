package com.ccs.bo;

import java.util.List;

import com.ccs.vo.AppReceiverVO;
import com.ccs.web.domain.AppInfoBean;

public interface IAppReceiverBO {
	
	public void create(AppReceiverVO vo) throws Exception ;
	
	public List<AppReceiverVO> findByIds(List<String> ids) throws Exception;
	
	public AppReceiverVO findById(String id) throws Exception;
	
	public void addManual(List<AppInfoBean> list) throws Exception;
	
	public void addInfo(List<AppInfoBean> list) throws Exception;
}
