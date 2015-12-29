package com.ccs.bo;

import com.ccs.vo.PowerInformationVO;

public interface IPowerInformationBO {
	void saveOrUpdate(PowerInformationVO piVO) throws Exception;
}
