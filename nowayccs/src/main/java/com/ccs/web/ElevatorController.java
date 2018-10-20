package com.ccs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.bo.IElevBO;
import com.ccs.vo.ElevatorVO;

@RestController
public class ElevatorController {
	
	@Autowired
	private IElevBO elevBO;

    @RequestMapping(value = "/elev/getelev/", method = RequestMethod.POST, produces="application/json;charset=UTF-8")  
	public @ResponseBody List<ElevatorVO> getElevator(@RequestParam String q) {
		return elevBO.getElevator(q);
	}
}
