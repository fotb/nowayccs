package com.ccs.web.domain;

import java.io.Serializable;
import java.util.List;

import com.ccs.vo.EntCategoryVO;

public class EntpriseCategoryDTO implements Serializable {

	private static final long serialVersionUID = 7907358985500676667L;

	private EntCategoryVO entCategoryVO;
	private List<EntpriseCategoryDTO> sonCategoryList;

	public EntCategoryVO getEntCategoryVO() {
		return entCategoryVO;
	}

	public void setEntCategoryVO(EntCategoryVO entCategoryVO) {
		this.entCategoryVO = entCategoryVO;
	}

	public List<EntpriseCategoryDTO> getSonCategoryList() {
		return sonCategoryList;
	}

	public void setSonCategoryList(List<EntpriseCategoryDTO> sonCategoryList) {
		this.sonCategoryList = sonCategoryList;
	}

}
