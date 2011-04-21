package com.ccs.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ccs.bo.IDictBO;
import com.ccs.util.Constants;
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
		List<DictVO> list = dictBO.findByType(Constants.DICT_DICTTYPE_LLFS);
		model.addAttribute("subDictlist", list);
		return "dict/dictlist";
	}
	

	
	
	@RequestMapping(params="action=search")
	public String searchDict(Dict dict, ModelMap model) {
		List<DictVO> list = dictBO.findByType(dict.getDictType());
		model.addAttribute("subDictlist", list);
		return "dict/dictlist";
	}
	
	
	@RequestMapping(params="action=add")
	public String addDict(String dictType, ModelMap model) {
		model.addAttribute(new Dict());
		return "dict/add";
	}
	
	public String saveDict(Dict dict, ModelMap model) {
		DictVO vo = new DictVO();
		vo.setDictType(dict.getDictType());
		vo.setSortIndex(dict.getSortIndex());
		vo.setValue(dict.getValue());
		
		dictBO.saveOrUpdate(vo);
		return "redirect:dict/dictlist";
	}

}
