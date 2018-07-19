package com.ccs.bo.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IAppReceiverBO;
import com.ccs.dao.IBaseDAO;
import com.ccs.vo.AppReceiverVO;
@Service("appReceiverBO")
@Transactional
public class AppReceiverBOImpl implements IAppReceiverBO {

	@Autowired
	private IBaseDAO<AppReceiverVO> appReceiverDAO;
	
	
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

}
