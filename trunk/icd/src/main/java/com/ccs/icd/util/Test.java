package com.ccs.icd.util;

import java.util.List;

import com.ccs.icd.bo.IRecordInfoBO;
import com.ccs.icd.vo.RecordInfoVO;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IRecordInfoBO recordBO = (IRecordInfoBO) IcdSpringUtil.getBean("recordInfoBO");
		RecordInfoVO vo = recordBO.findById("111", 2011);
		System.out.println("2011--" + vo.getCalleeNo());
		
		List<RecordInfoVO> list = recordBO.findRecordInfo("13958186722", "111121", "201112212134", 2011);
		for (RecordInfoVO recordInfoVO : list) {
			System.out.println("2011--" + recordInfoVO.getCalleeNo());
		}
		
		
		RecordInfoVO vo1 = recordBO.findById("111", 2012);
		System.out.println("2012--" + vo1.getCalleeNo());
		
		List<RecordInfoVO> list1 = recordBO.findRecordInfo("13958186722", "111121", "201112212134", 2012);
		for (RecordInfoVO recordInfoVO : list1) {
			System.out.println("2012--" + recordInfoVO.getCalleeNo());
		}
	}

}
