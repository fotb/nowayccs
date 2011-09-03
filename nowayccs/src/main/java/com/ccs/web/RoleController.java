package com.ccs.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IRoleBO;
import com.ccs.util.PageInfo;
import com.ccs.util.Utils;
import com.ccs.vo.OperationVO;
import com.ccs.vo.RoleOperationVO;
import com.ccs.vo.RoleVO;

@Controller
@RequestMapping("/role.do")
public class RoleController {

	@Autowired
	private IRoleBO roleBO;
	
	@RequestMapping
	public String init(@RequestParam(value="pageNo", required=false) String pageNo, 
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if(Utils.isNull(pageNo)) { 
		pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		
		List<RoleVO> list = roleBO.findAllRole(pageInfo);
		model.addAttribute("roleList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "role/list";
	}
	
	@RequestMapping(params="action=add")
	public String add(String pageNo, ModelMap model) {
		List<OperationVO> list = roleBO.findAllOperation();
		model.addAttribute("operationList", list);
		model.addAttribute("pageNo", pageNo);
		return "role/add";
	}

	@RequestMapping(params="action=doSave")
	public String save(@RequestParam(value="roleId", required=false) String roleId,
			@RequestParam("roleName") String roleName, 
			@RequestParam(value="operationIds", required=false) String operationIds, 
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		RoleVO vo = new RoleVO();
		if(null != roleId) {
			vo = roleBO.findRoleById(roleId);
		}
		vo.setRoleName(roleName);
		String[] idAry = null;
		if(null != operationIds) {
			idAry = operationIds.split(",");
		}
		roleBO.addRole(vo, idAry);
		return "redirect:role.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params="action=edit")
	public String edit(@RequestParam("roleId") String roleId, 
			String pageNo, ModelMap model) {
		List<OperationVO> list = roleBO.findAllOperation();
		RoleVO roleVO = roleBO.findRoleById(roleId);
		List<RoleOperationVO> roleOperationVOList = roleBO.findRoleOperationByRoleId(roleId);
		Map<String, RoleOperationVO> operationIdMap = new HashMap<String, RoleOperationVO>();
		for (Iterator<RoleOperationVO> iter = roleOperationVOList.iterator(); iter
				.hasNext();) {
			RoleOperationVO roleOperationVO = (RoleOperationVO) iter.next();
			operationIdMap.put(roleOperationVO.getId().getOperationId(), roleOperationVO);
		}
		model.addAttribute("roleVO", roleVO);
		model.addAttribute("operationIdMap", operationIdMap);
		model.addAttribute("operationList", list);
		model.addAttribute("pageNo", pageNo);
		return "role/edit";
	}
	
	@RequestMapping(params="action=view")
	public String view(@RequestParam("roleId") String roleId, 
			String pageNo, ModelMap model) {
		List<OperationVO> list = roleBO.findAllOperation();
		RoleVO roleVO = roleBO.findRoleById(roleId);
		List<RoleOperationVO> roleOperationVOList = roleBO.findRoleOperationByRoleId(roleId);
		Map<String, RoleOperationVO> operationIdMap = new HashMap<String, RoleOperationVO>();
		for (Iterator<RoleOperationVO> iter = roleOperationVOList.iterator(); iter
				.hasNext();) {
			RoleOperationVO roleOperationVO = (RoleOperationVO) iter.next();
			operationIdMap.put(roleOperationVO.getId().getOperationId(), roleOperationVO);
		}
		model.addAttribute("roleVO", roleVO);
		model.addAttribute("operationIdMap", operationIdMap);
		model.addAttribute("operationList", list);
		model.addAttribute("pageNo", pageNo);
		return "role/view";
	}
	
	@RequestMapping(params="action=delete")
	public String delete(@RequestParam("roleId") String roleId,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		RoleVO vo = roleBO.findRoleById(roleId);
		roleBO.deleteRole(vo);
		return "redirect:role.do?pageNo=" + pageNo;
	}
}
