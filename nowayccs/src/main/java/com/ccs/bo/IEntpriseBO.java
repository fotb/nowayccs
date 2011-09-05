package com.ccs.bo;

import java.util.List;
import java.util.Map;

import com.ccs.util.PageInfo;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.EntpriseVO;

public interface IEntpriseBO {

	List<EntpriseVO> findAllEntprise(PageInfo pageInfo);

	void addEntprise(EntpriseVO vo);

	List<EntCategoryVO> findEntCategoryByParentId(String parentId);

	List<EntpriseVO> findByParams(final String entpriseName,
			final String entpriseNo, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, PageInfo pageInfo);
	
	Map<String, List<EntCategoryVO>> findAllCategory();
}
