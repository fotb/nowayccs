package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IEventBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.EventCategoryVO;

@Service("eventBO")
@Transactional
public class EventBOImpl implements IEventBO {

	@Autowired
	private IBaseDAO<EventCategoryVO> eventCategoryDAO;
	@Override
	public List<EventCategoryVO> getEventCategory() throws Exception {
		final String sql = "select * from SGPT_EVENTCATEGORY t start with t.code='0' connect by (prior t.code) = t.parentcode";
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>)eventCategoryDAO.createSQLQuery(sql, new Object[] {}, new Type[]{});
		List<EventCategoryVO> voList = new ArrayList<EventCategoryVO>();
		for (Object[] objs : list) {
			EventCategoryVO vo = new EventCategoryVO();
			vo.setPid(String.valueOf(objs[0]));
			vo.setName(String.valueOf(objs[1]));
			vo.setCode(String.valueOf(objs[2]));
			vo.setParentCode(String.valueOf(objs[3]));
			voList.add(vo);
		}
		return voList;
	}
	@Override
	public EventCategoryVO getEventCategory(String code) throws Exception {
		final String hql = "from EventCategoryVO where code = ?";
		List<EventCategoryVO> list = eventCategoryDAO.queryForObject(hql, new Object[] {code});
		return list.isEmpty() ? null : list.get(0);
	}
}
