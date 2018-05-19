package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IMessageDAO;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.MessageVO;

@Repository("messageDAO")
public class MessageDAOImpl extends DefaultDAOSupport implements IMessageDAO {

	@Override
	public void saveOrUpdate(MessageVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(MessageVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public MessageVO findById(String msgId) {
		return getHibernateTemplate().get(MessageVO.class, msgId);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<MessageVO> findByParams(final String msgType, final String creator,
			final String title, final String startDt, final String endDt, final PageInfo pageInfo) {
		return (List<MessageVO>) getHibernateTemplate().execute(new HibernateCallback<List<MessageVO>>() {
			@Override
			public List<MessageVO> doInHibernate(Session session) throws HibernateException {
				List<Object> objs = new ArrayList<Object>();
				List<Type> types = new ArrayList<Type>();
				StringBuffer buffer = new StringBuffer(1000);
				buffer.append("from MessageVO t where ");
				buffer.append("(t.messageType = ? or ? is null) and ");
				objs.add(msgType);
				objs.add(msgType);
				types.add(StandardBasicTypes.STRING);
				types.add(StandardBasicTypes.STRING);
				buffer.append("(t.creator = ? or ? is null) and ");

				objs.add(creator);
				objs.add(creator);
				types.add(StandardBasicTypes.STRING);
				types.add(StandardBasicTypes.STRING);
				buffer.append("(t.title like ? or ? is null) ");
				objs.add("%" + title + "%");
				objs.add(title);
				types.add(StandardBasicTypes.STRING);
				types.add(StandardBasicTypes.STRING);
				if(!StringUtil.isNull(startDt)) {				
					buffer.append("and ");
					buffer.append("(trunc(t.createDate) >= ? or ? is null) ");
					objs.add(DateUtil.parse(startDt, "yyyy-MM-dd"));
					objs.add(DateUtil.parse(startDt, "yyyy-MM-dd"));
					types.add(StandardBasicTypes.DATE);
					types.add(StandardBasicTypes.DATE);
				}
				if(!StringUtil.isNull(endDt)) {
					buffer.append("and ");
					buffer.append("(trunc(t.createDate) <= ? or ? is null) ");
					objs.add(DateUtil.parse(endDt, "yyyy-MM-dd"));
					objs.add(DateUtil.parse(endDt, "yyyy-MM-dd"));
					types.add(StandardBasicTypes.DATE);
					types.add(StandardBasicTypes.DATE);
				}
				buffer.append("order by t.createDate DESC");
				
				Type[] typeAry = new Type[types.size()]; 
				int i = 0;
				for (Type type : types) {
					typeAry[i] = type; 
					i++;
				}
				Query query = session.createQuery(buffer.toString());
				query.setParameters(objs.toArray(), typeAry);
				query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
				query.setMaxResults(pageInfo.getPAGE_COUNT());
				return query.list();
			}
			
		});
	}

	@Override
	public int getCountByParams(String msgType, String creator, String title,
			String startDt, String endDt) {
		List<Object> objs = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("select count(t.messageId) from MessageVO t where ");
		buffer.append("(t.messageType = ? or ? is null) and ");
		objs.add(msgType);
		objs.add(msgType);
		buffer.append("(t.creator = ? or ? is null) and ");

		objs.add(creator);
		objs.add(creator);
		buffer.append("(t.title like ? or ? is null) ");
		objs.add("%" + title + "%");
		objs.add(title);
		if(!StringUtil.isNull(startDt)) {
			buffer.append(" and ");
			buffer.append("(trunc(t.createDate) >= ? or ? is null) ");
			objs.add(DateUtil.parse(startDt, "yyyy-MM-dd"));
			objs.add(DateUtil.parse(startDt, "yyyy-MM-dd"));
		}
		if(!StringUtil.isNull(endDt)) {
			buffer.append(" and ");
			buffer.append("(trunc(t.createDate) <= ? or ? is null) ");
			objs.add(DateUtil.parse(endDt, "yyyy-MM-dd"));
			objs.add(DateUtil.parse(endDt, "yyyy-MM-dd"));
		}
		
		final Long count = (Long) getHibernateTemplate().find(buffer.toString(), objs.toArray()).listIterator().next();
		return count.intValue();
	}

}
