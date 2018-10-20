package com.ccs.bo;

import java.util.List;

import com.ccs.vo.EventCategoryVO;

public interface IEventBO {
	
	public List<EventCategoryVO> getEventCategory() throws Exception;
	
	public EventCategoryVO getEventCategory(String code) throws Exception;

}
