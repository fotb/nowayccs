package com.ccs.bo;

import com.ccs.vo.LonelyManInfoVO;

public interface ILonelyFamilyBO {
	LonelyManInfoVO findLonelyManInfo(final String telPhone);
}
