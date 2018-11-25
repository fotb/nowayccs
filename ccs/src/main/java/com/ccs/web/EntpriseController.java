package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.ClassOfEntpriseVO;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.EntSearch;
import com.ccs.web.domain.EntpriseCategoryDTO;

@Controller
@RequestMapping("/entprise.do")
public class EntpriseController {

	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private IUserBO userBO;

	@RequestMapping
	public String list(
			@ModelAttribute("entSearch") EntSearch entSearch,
			@RequestParam(value = "pageNo", required = false) String pageNo, HttpSession session, 
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if (StringUtil.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}

		if (null == entSearch) {
			entSearch = new EntSearch();
		}

		String parentCategoryId = getSelectedValue(entSearch.getParentCategoryId());
		String subParentCategoryId = getSelectedValue(entSearch.getSubParentCategoryId());
		String categoryId = getSelectedValue(entSearch.getCategoryId());
		String servicesType = getSelectedValue(entSearch.getServicesType());
				
		
		List<EntpriseVO> entpriseList = entpriseBO.findByParams(
				entSearch.getEntpriseName(), entSearch.getEntpriseNo(), servicesType, 
				parentCategoryId, subParentCategoryId, categoryId,
				null, entSearch.getAddress(), pageInfo);
		model.addAttribute("entpriseList", entpriseList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("entSearch", entSearch);
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(userBO.hasOperationRight(user.getUserId(), Constants.SYS_PERMISSION_FWQYWH)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}
		return "ent/list";
	}

	private String getSelectedValue(String value) {
		String result = "";
		if(!StringUtil.isNull(value)) {
			result = value;
		}
		return result;
	}
	
	@RequestMapping(params = "action=getCategory", method = RequestMethod.GET)
	public @ResponseBody List<EntCategoryVO>  getCategory(@RequestParam String parentId) throws UnsupportedEncodingException {
//		List<EntCategoryVO> list = entpriseBO.findEntCategoryByParentId(parentId);
//		JSONArray jsonObj = JSONArray.fromObject(list);
		return  entpriseBO.findEntCategoryByParentId(parentId);
	}
	
	
	@RequestMapping(params = "action=add")
	public String add(@RequestParam("pageNo") String pageNo, ModelMap model) {
		EntpriseVO entpriseVO = new EntpriseVO();
		model.addAttribute(entpriseVO);
		model.addAttribute("pageNo", pageNo);
		return "ent/add";
	}
	
	@RequestMapping(params = "action=save")
	public String save(@ModelAttribute("entpriseVO") EntpriseVO entpriseVO,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		entpriseBO.addEntprise(entpriseVO);
		return "redirect:entprise.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=update")
	public String update(@RequestParam("entpriseId") String entpriseId,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		EntpriseVO entpriseVO = entpriseBO.findEntByEntpriseId(entpriseId);
		model.addAttribute(entpriseVO);
		model.addAttribute("pageNo", pageNo);
		return "ent/edit";
	}
	
	@RequestMapping(params = "action=view")
	public String view(@RequestParam("entpriseId") String entpriseId,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		EntpriseVO entpriseVO = entpriseBO.findEntByEntpriseId(entpriseId);
		model.addAttribute(entpriseVO);
		model.addAttribute("pageNo", pageNo);
		return "ent/view";
	}
	
	@RequestMapping(params = "action=classview")
	public String viewClass(@RequestParam("entpriseId") String entpriseId, @RequestParam("pageNo") String pageNo, 
			HttpSession session, ModelMap model) {
		Map<String, List<EntCategoryVO>> map = entpriseBO.findAllCategory();
		List<EntpriseCategoryDTO> list = getEntCategoryDTO(map, Constants.TOP_CODE);
		model.addAttribute("dtoList", list);
		
		EntpriseVO entpriseVO = entpriseBO.findEntByEntpriseId(entpriseId);
		model.addAttribute("entpriseVO", entpriseVO);
		
		List<ClassOfEntpriseVO> coeVOList = entpriseBO.findCOEByEntpriseId(entpriseId);
		Map<String, ClassOfEntpriseVO> coeMap = new HashMap<String, ClassOfEntpriseVO>();
		for (Iterator<ClassOfEntpriseVO> iter = coeVOList.iterator(); iter.hasNext();) {
			ClassOfEntpriseVO coeVO = (ClassOfEntpriseVO) iter.next();
			coeMap.put(coeVO.getId().getCategory(), coeVO);
		}
		model.addAttribute("coeMap", coeMap);
		model.addAttribute("pageNo", pageNo);
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(userBO.hasOperationRight(user.getUserId(), Constants.SYS_PERMISSION_FWQYWH)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}
		return "ent/classview";
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
	
	@RequestMapping(params = "action=updateclass")
	public String updateClass(@RequestParam("entpriseId") String entpriseId, 
			@RequestParam("pageNo") String pageNo, 
			@RequestParam(value="categoryId", required=false) String categoryId, ModelMap model) {
		List<String> categoryIdList = new ArrayList<String>();
		if(null != categoryId) {
			String[] categoryIds = categoryId.split(",");
			categoryIdList = Arrays.asList(categoryIds);
		}
		entpriseBO.addCategoryToEntprise(entpriseId, categoryIdList);
		return "redirect:entprise.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=changestatus")
	public String changeStatus(@RequestParam("entpriseId") String entpriseId, 
			@RequestParam("pageNo") String pageNo, 
			@RequestParam("status") String status, ModelMap model) {
		EntpriseVO vo = entpriseBO.findEntByEntpriseId(entpriseId);
		vo.setStatus(status);
		entpriseBO.updateEntprise(vo);
		return "redirect:entprise.do?pageNo=" + pageNo;
	}
}
