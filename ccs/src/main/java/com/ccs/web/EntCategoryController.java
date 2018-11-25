package com.ccs.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IEntCategoryBO;
import com.ccs.util.Constants;
import com.ccs.util.StringUtil;
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
	
    @RequestMapping
	public String init(EntCategory entCategory, ModelMap model) {
		entCategory.setParentId(StringUtil.isNull(entCategory.getParentId()) ? String.valueOf(Constants.TOP_CODE) : entCategory.getParentId());
		entCategory.setSubParentId(StringUtil.isNull(entCategory.getSubParentId()) ? String.valueOf(Constants.TOP_CODE) : entCategory.getSubParentId());
		model.addAttribute("entCategory", entCategory);
//		
//		model.addAttribute("topCategoryList", entCategoryBO.findByParentId(Constants.TOP_CODE));
//		List<EntCategoryVO> subCategoryList = entCategoryBO.findByParentId(entCategory.getParentId());
//		List<EntCategoryVO> categoryList = entCategoryBO.findByParentId(entCategory.getSubParentId());
//		model.addAttribute("subCategoryList", subCategoryList);
//		model.addAttribute("categoryList", categoryList);
		
		List<EntCategoryVO> subCategoryList = new ArrayList<EntCategoryVO>();
		List<EntCategoryVO> categoryList = new ArrayList<EntCategoryVO>(); 
		if(Constants.TOP_CODE.equals(entCategory.getParentId())) {
			categoryList = entCategoryBO.findByParentId(Constants.TOP_CODE);
		} else if(Constants.TOP_CODE.equals(entCategory.getSubParentId())){
			subCategoryList = entCategoryBO.findByParentId(entCategory.getParentId());
			categoryList = entCategoryBO.findByParentId(entCategory.getParentId());
		} else {
			subCategoryList = entCategoryBO.findByParentId(entCategory.getParentId());
			categoryList = entCategoryBO.findByParentId(entCategory.getSubParentId());
		}
		 
		model.addAttribute("topCategoryList", entCategoryBO.findByParentId(Constants.TOP_CODE));
		model.addAttribute("subCategoryList", subCategoryList);
		model.addAttribute("categoryList", categoryList);
		
		return "entcategory/list";
	}

    @RequestMapping(params="action=search")
	public String search(@RequestParam("parentId") String parentId, @RequestParam("subParentId") String subParentId, ModelMap model) {
    	EntCategory entCategory = new EntCategory();
    	entCategory.setParentId(parentId);
    	entCategory.setSubParentId(subParentId);
		model.addAttribute("entCategory", entCategory);
		model.addAttribute("topCategoryList", entCategoryBO.findByParentId(Constants.TOP_CODE));
		
		List<EntCategoryVO> subCategoryList = new ArrayList<EntCategoryVO>();
		List<EntCategoryVO> categoryList = new ArrayList<EntCategoryVO>(); 
		if(Constants.TOP_CODE.equals(entCategory.getParentId())) {
			categoryList = entCategoryBO.findByParentId(Constants.TOP_CODE);
		} else if(Constants.TOP_CODE.equals(entCategory.getSubParentId())){
			subCategoryList = entCategoryBO.findByParentId(entCategory.getParentId());
			categoryList = entCategoryBO.findByParentId(entCategory.getParentId());
		} else {
			subCategoryList = entCategoryBO.findByParentId(entCategory.getParentId());
			categoryList = entCategoryBO.findByParentId(entCategory.getSubParentId());
		}
		 
		model.addAttribute("subCategoryList", subCategoryList);
		model.addAttribute("categoryList", categoryList);
		
		return "entcategory/list";
	}
    
    @RequestMapping(params="action=add")
    public String add(EntCategory entCategory, ModelMap model) {
    	model.addAttribute("entCategory", entCategory);
    	return "entcategory/add";
    }
    
    
    @RequestMapping(params="action=addsave")
    public String addSave(EntCategory entCategory, ModelMap model) {
    	EntCategoryVO vo = new EntCategoryVO();
    	if(Constants.TOP_CODE.equals(entCategory.getSubParentId())) {
    		vo.setParentId(entCategory.getParentId());
    	} else {
    		vo.setParentId(entCategory.getSubParentId());
    	} 
    	vo.setValue(entCategory.getValue());
    	entCategoryBO.saveOrUpdate(vo);
    	model.addAttribute("parentId", entCategory.getParentId());
    	model.addAttribute("subParentId", entCategory.getSubParentId());
    	return "redirect:entcategory.do?action=search";
    }
    
    @RequestMapping(params="action=edit")
    public String edit(EntCategory entCategory, String categoryId, ModelMap model) {
    	EntCategoryVO vo = entCategoryBO.findById(categoryId);
    	entCategory.setValue(vo.getValue());
    	entCategory.setCategoryId(categoryId);
    	model.addAttribute("entCategory", entCategory);
    	
    	return "entcategory/edit";
    }
    
    @RequestMapping(params="action=editsave")
    public String editSave(EntCategory entCategory, ModelMap model) {
    	EntCategoryVO vo = entCategoryBO.findById(entCategory.getCategoryId());
    	vo.setValue(entCategory.getValue());
    	entCategoryBO.saveOrUpdate(vo);
    	model.addAttribute("parentId", entCategory.getParentId());
    	model.addAttribute("subParentId", entCategory.getSubParentId());
    	return "redirect:entcategory.do?action=search";
    }
    
    @RequestMapping(params="action=del")
    public String delete(EntCategory entCategory, String delId, ModelMap model) {
    	EntCategoryVO vo = entCategoryBO.findById(delId);
    	entCategoryBO.delete(vo);
    	model.addAttribute("parentId", entCategory.getParentId());
    	model.addAttribute("subParentId", entCategory.getSubParentId());
    	return "redirect:entcategory.do?action=search";
    }
}
