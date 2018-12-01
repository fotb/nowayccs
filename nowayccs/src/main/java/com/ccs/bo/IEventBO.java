package com.ccs.bo;

import java.util.List;

import com.ccs.vo.EventCategoryVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;

public interface IEventBO {
	
	public List<EventCategoryVO> getEventCategory() throws Exception;
	
	public EventCategoryVO getEventCategory(String code) throws Exception;
	
	void acceptSGPT(InformationVO vo, EventVO eventVO) throws Exception;
	
	void pushEvent(EventVO vo) throws Exception;
	
	void updateEventStatus(String serialNum, String status) throws Exception;
	
	public List<EventVO> getUnUploadEvent();
	
	void queryEvent(EventVO vo);
	
	void processEvent() throws Exception;

}
