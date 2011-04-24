package com.ccs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccs.bo.IEntCategoryBO;
import com.ccs.util.Constants;
import com.ccs.vo.EntCategoryVO;
import com.ccs.web.domain.EntCategory;

@Controller
@RequestMapping("/entcategory.do")
public class EntCategoryController {
	
	@Autowired
	private IEntCategoryBO entCategoryBO;
	
//    @ModelAttribute("entCategory")
//    public List<EntCategoryVO> populateItems() {
//    	return entCategoryBO.findByParentId("-1");
//    }
	
    @RequestMapping(method=RequestMethod.GET)
	public String init(ModelMap model) {
		EntCategory entCategory = new EntCategory();
		entCategory.setCategoryId(String.valueOf(Constants.TOP_CODE));
		entCategory.setSubCategoryId(String.valueOf(Constants.TOP_CODE));
		model.addAttribute("entCategory", entCategory);
		model.addAttribute("topCategoryList", entCategoryBO.findByParentId("-1"));
		return "entcategory/list";
	}

    @RequestMapping(params="action=search")
	public String search(EntCategory entCategory, ModelMap model) {
		model.addAttribute("entCategory", entCategory);
		model.addAttribute("topCategoryList", entCategoryBO.findByParentId("-1"));
		
		List<EntCategoryVO> subCategoryList = new ArrayList<EntCategoryVO>();
		List<EntCategoryVO> categoryList = new ArrayList<EntCategoryVO>(); 
		if("-1".equals(entCategory.getCategoryId())) {
			categoryList = entCategoryBO.findByParentId("-1");
		} else if("-1".equals(entCategory.getSubCategoryId())){
			subCategoryList = entCategoryBO.findByParentId(entCategory.getCategoryId());
			categoryList = entCategoryBO.findByParentId(entCategory.getCategoryId());
		} else {
			subCategoryList = entCategoryBO.findByParentId(entCategory.getCategoryId());
			categoryList = entCategoryBO.findByParentId(entCategory.getSubCategoryId());
		}
		 
		model.addAttribute("subCategoryList", subCategoryList);
		model.addAttribute("categoryList", categoryList);
		
		return "entcategory/list";
	}
    
    @RequestMapping(params="action=del")
    public String delete(String delId, ModelMap model) {
    	EntCategoryVO vo = entCategoryBO.findById(delId);
    	entCategoryBO.delete(vo);
    	return "redirect:entcategory.do?action=del";
    }
}
