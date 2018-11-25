package com.ccs.bo;

import java.util.List;
import java.util.Map;

import com.ccs.util.PageInfo;
import com.ccs.vo.ClassOfEntpriseVO;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.EntpriseVO;

public interface IEntpriseBO {

	List<EntpriseVO> findAllEntprise(PageInfo pageInfo);

	void addEntprise(EntpriseVO vo);

	void updateEntprise(EntpriseVO vo);
	
	List<EntCategoryVO> findEntCategoryByParentId(String parentId);

	List<EntpriseVO> findByParams(final String entpriseName,
			final String entpriseNo, final String servicesType, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, final String address, PageInfo pageInfo);
	
	Map<String, List<EntCategoryVO>> findAllCategory();
	
	EntpriseVO findEntByEntpriseId(String entpriseId);
	
	List<ClassOfEntpriseVO> findCOEByEntpriseId(String entpriseId);
	
	void addCategoryToEntprise(String entpriseId, List<String> categoryIdList);
	
	Map<String, EntpriseVO> findAll2Map();
}
