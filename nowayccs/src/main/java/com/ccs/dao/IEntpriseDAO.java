package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.EntpriseVO;

public interface IEntpriseDAO {

	EntpriseVO findById(String entpriseId);

	void saveOrUpdate(EntpriseVO vo);

	void delete(EntpriseVO vo);

	int getTotalCount();

	List<EntpriseVO> findAll(PageInfo pageInfo);

	List<EntpriseVO> findByParams(final String entpriseName,
			final String entpriseNo, final String bigEntclassId,
			final String smallEntclassId, final String entclassId,
			final String status, final PageInfo pageInfo);

	int getTotalCount(final String entpriseName, final String entpriseNo,
			final String bigEntclassId, final String smallEntclassId,
			final String entclassId, final String status);
}
