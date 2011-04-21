package com.ccs.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IDictBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.Utils;
import com.ccs.vo.DictVO;
import com.ccs.web.domain.Dict;

@Controller                   
@RequestMapping("/dict.do")
public class DictController {
	
	@Autowired
	private IDictBO dictBO;
	
    @ModelAttribute("dictType")
    public List<DictVO> populateItems() {
    	List<DictVO> list = new ArrayList<DictVO>();
    	for(Iterator<String> iter = Constants.DICT_DICTTYPE_HASHMAP.keySet().iterator(); iter.hasNext();) {
    		String key = iter.next();
    		DictVO vo = new DictVO();
    		vo.setValue(Constants.DICT_DICTTYPE_HASHMAP.get(key));
    		vo.setDictType(key);
    		list.add(vo);
    	}
        return list;
    }
	
    
    
	@RequestMapping(method=RequestMethod.GET)
	public String init(ModelMap model) {
		Dict dict = new Dict();
		dict.setDictType(Constants.DICT_DICTTYPE_LLFS);
		model.addAttribute(dict);
		PageInfo pageInfo=new PageInfo();
		pageInfo.setCurrentPage(1);
		List<DictVO> list = dictBO.findByType(Constants.DICT_DICTTYPE_LLFS, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("subDictlist", list);
		return "dict/dictlist";
	}
	
	@RequestMapping(params="action=search")
	public String searchDict(Dict dict, @RequestParam("dictType") String dictType , @RequestParam("pageNo") String pageNo, ModelMap model) {
		PageInfo pageInfo=new PageInfo();
		if(Utils.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));	
		}
		List<DictVO> list = dictBO.findByType(Utils.isNull(dictType) ? dict.getDictType() : dictType, pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("subDictlist", list);
		return "dict/dictlist";
	}
	
	@RequestMapping(params="action=add")
	public String addDict(String dictType, String pageNo, ModelMap model) {
		Dict dict = new Dict();
		dict.setDictType(dictType);
		model.addAttribute(dict);
		model.addAttribute("pageNo", pageNo);
		return "dict/add";
	}
	@RequestMapping(params="action=save")
	public String saveDict(Dict dict, @RequestParam("pageNo") String pageNo, ModelMap model) {
		DictVO vo = new DictVO();
		vo.setDictType(dict.getDictType());
		vo.setSortIndex(dict.getSortIndex());
		vo.setValue(dict.getValue());
		dictBO.saveOrUpdate(vo);
		model.addAttribute("pageNo", "1");
		model.addAttribute("dictType", dict.getDictType());
		return "redirect:dict.do?action=search";
	}

	@RequestMapping(params="action=del")
	public String delete(String dictId, ModelMap model) {
		DictVO vo = dictBO.findById(dictId);
		dictBO.delete(vo);
		return "redirect:dict.do?action=search";
	}
	
	@RequestMapping(params="action=edit")
	public String editDict(String dictId, String pageNo, ModelMap model) {
		DictVO vo = dictBO.findById(dictId);
		Dict dict = new Dict();
		dict.setDictId(vo.getDictId());
		dict.setDictType(vo.getDictType());
		dict.setSortIndex(vo.getSortIndex());
		dict.setValue(vo.getValue());
		model.addAttribute(dict);
		model.addAttribute("pageNo", pageNo);
		return "dict/edit";
	}
	
	@RequestMapping(params="action=editsave")
	public String editSaveDict(@Valid Dict dict, @RequestParam("pageNo") String pageNo, ModelMap model) {
		DictVO vo = dictBO.findById(dict.getDictId());
		vo.setDictType(dict.getDictType());
		vo.setSortIndex(dict.getSortIndex());
		vo.setValue(dict.getValue());		
		dictBO.saveOrUpdate(vo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("dictType", dict.getDictType());
		return "redirect:dict.do?action=search";
	}
}
