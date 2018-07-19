package com.ccs.bo;

import java.util.List;

import com.ccs.vo.AppReceiverVO;

public interface IAppReceiverBO {
	
	public void create(AppReceiverVO vo) throws Exception ;
	
	public List<AppReceiverVO> findByIds(List<String> ids) throws Exception;
	
	public AppReceiverVO findById(String id) throws Exception;
}
