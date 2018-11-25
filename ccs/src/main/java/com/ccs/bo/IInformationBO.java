package com.ccs.bo;

import com.ccs.vo.InformationVO;

public interface IInformationBO {

	InformationVO findById(String infoId);
}
