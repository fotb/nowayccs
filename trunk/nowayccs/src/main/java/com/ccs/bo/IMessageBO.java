package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.MessageVO;

public interface IMessageBO {
	void saveOrUpdate(MessageVO vo);

	void delete(MessageVO vo);

	MessageVO findById(String msgId);

	List<MessageVO> findByParams(String msgType, String creator, String title,
			String startDt, String endDt, PageInfo pageInfo);
}
