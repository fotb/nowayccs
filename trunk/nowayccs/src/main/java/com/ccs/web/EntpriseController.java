package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IEntpriseBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.Utils;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.EntpriseVO;
import com.ccs.web.domain.EntSearch;
import com.ccs.web.domain.EntpriseCategoryDTO;

@Controller
@RequestMapping("/entprise.do")
public class EntpriseController {

	@Autowired
	private IEntpriseBO entpriseBO;

	@RequestMapping
	public String list(
			@ModelAttribute("entSearch") EntSearch entSearch,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if (Utils.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}

		if (null == entSearch) {
			entSearch = new EntSearch();
		}

		String parentCategoryId = getCategoryValue(entSearch.getParentCategoryId());
		String subParentCategoryId = getCategoryValue(entSearch.getSubParentCategoryId());
		String categoryId = getCategoryValue(entSearch.getCategoryId());
		
				
		
		List<EntpriseVO> entpriseList = entpriseBO.findByParams(
				entSearch.getEntpriseName(), entSearch.getEntpriseNo(),
				parentCategoryId, subParentCategoryId, categoryId,
				null, pageInfo);
		model.addAttribute("entpriseList", entpriseList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute(entSearch);
		return "ent/list";
	}



	private String getCategoryValue(String parentCategoryId) {
		String result = "";
		if(!EntSearch.DEFAULT_VALUE_SELECT.equals(parentCategoryId)) {
			result = parentCategoryId;
		}
		return result;
	}
	
	@RequestMapping(params = "action=getCategory", method = RequestMethod.GET)
	public @ResponseBody String getCategory(@RequestParam String parentId) throws UnsupportedEncodingException {
		List<EntCategoryVO> list = entpriseBO.findEntCategoryByParentId(parentId);
		JSONArray jsonObj = JSONArray.fromObject(list);
		return jsonObj.toString();
	}
	
	
	@RequestMapping(params = "action=add")
	public String add(@RequestParam("pageNo") String pageNo, ModelMap model) {
		EntpriseVO entpriseVO = new EntpriseVO();
		model.addAttribute(entpriseVO);
		model.addAttribute("pageNo", pageNo);
		return "ent/add";
	}
	
	@RequestMapping(params = "action=save")
	public String save(@ModelAttribute("entpriseVO") EntpriseVO entpriseVO, @RequestParam("pageNo") String pageNo, ModelMap model) {
		entpriseBO.addEntprise(entpriseVO);
		return "redirect:entprise.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=classview")
	public String viewClass(@RequestParam("entpriseId") String entpriseId, @RequestParam("pageNo") String pageNo, ModelMap model) {
		Map<String, List<EntCategoryVO>> map = entpriseBO.findAllCategory();
		List<EntpriseCategoryDTO> list = new ArrayList<EntpriseCategoryDTO>();
		List<EntCategoryVO> parentCategoryList = map.get(Constants.TOP_CODE);
		for (Iterator<EntCategoryVO> iter = parentCategoryList.iterator(); iter.hasNext();) {
			EntCategoryVO ecVO = (EntCategoryVO) iter.next();
			EntpriseCategoryDTO parentDTO = new EntpriseCategoryDTO();
			parentDTO.setEntCategoryVO(ecVO);
			List<EntCategoryVO> subParentCategoryList = map.get(ecVO.getCategoryId());
			List<EntpriseCategoryDTO> subList = new ArrayList<EntpriseCategoryDTO>();
			for (Iterator<EntCategoryVO> subIter = subParentCategoryList.iterator(); subIter.hasNext();) {
				EntCategoryVO entCategoryVO = (EntCategoryVO) subIter.next();
				EntpriseCategoryDTO subParentDTO = new EntpriseCategoryDTO();
				subParentDTO.setEntCategoryVO(entCategoryVO);
				List<EntCategoryVO> categoryList = map.get(entCategoryVO.getCategoryId());
				List<EntpriseCategoryDTO> dtoList = new ArrayList<EntpriseCategoryDTO>();
				for (Iterator<EntCategoryVO> categoryIter = categoryList.iterator(); categoryIter.hasNext();) {
					EntCategoryVO categoryVO = (EntCategoryVO) categoryIter.next();
					EntpriseCategoryDTO dto = new EntpriseCategoryDTO();
					dto.setEntCategoryVO(categoryVO);
					dtoList.add(dto);
				}
				subParentDTO.setSonCategoryList(dtoList);
				subList.add(subParentDTO);
			}
			parentDTO.setSonCategoryList(subList);
			list.add(parentDTO);
		}
		return "";
	}
	
	private List<EntpriseCategoryDTO> getEntCategoryDTO(Map<String, List<EntCategoryVO>> map, String parentId) {
		List<EntpriseCategoryDTO> dtoList = new ArrayList<EntpriseCategoryDTO>();
		List<EntCategoryVO> categoryList = map.get(parentId);
		if(null != categoryList) {
			for (Iterator<EntCategoryVO> iter = categoryList.iterator(); iter.hasNext();) {
				EntCategoryVO entCategoryVO = (EntCategoryVO) iter.next();
				EntpriseCategoryDTO dto = new EntpriseCategoryDTO();
				dto.setEntCategoryVO(entCategoryVO);
				dto.setSonCategoryList(getEntCategoryDTO(map, entCategoryVO.getCategoryId()));
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
