package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.InformationVO;

public interface IBizLifeBackVstBO {

	List<InformationVO> findByDeliverer(String deliverer, String helpType, PageInfo pageInfo);
}
