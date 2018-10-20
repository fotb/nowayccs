package com.ccs.bo;

import java.util.List;

import com.ccs.vo.ElevatorVO;

public interface IElevBO {

	public List<ElevatorVO> getElevator(String q);
	
}
