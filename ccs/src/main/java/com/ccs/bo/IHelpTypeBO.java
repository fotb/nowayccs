package com.ccs.bo;

import java.util.List;

import com.ccs.vo.HelpTypeVO;

public interface IHelpTypeBO {

		public List<HelpTypeVO> getList(String parentId);
}
