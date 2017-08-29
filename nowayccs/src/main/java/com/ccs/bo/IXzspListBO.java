package com.ccs.bo;

import java.util.List;

import com.ccs.util.EasyUiTree;
import com.ccs.vo.UserVO;
import com.ccs.vo.XzspListVO;
import com.ccs.web.domain.XzspListDomain;

public interface IXzspListBO {
	
	public void save(XzspListDomain domain, UserVO user) throws Exception;
	public void save(XzspListVO vo) throws Exception;
	
	public List<XzspListVO> findAll() throws Exception;
	
	public XzspListVO findById(final String pid) throws Exception;
	
	public void editSave(XzspListDomain domain, UserVO userVO) throws Exception;
	
	public void del(final String pid) throws Exception;
	
	public EasyUiTree buildListCodeTree() throws Exception;
	
	public List<XzspListVO> search(String key) throws Exception;
	
	public List<XzspListVO> findByListCode(String code) throws Exception;

}
