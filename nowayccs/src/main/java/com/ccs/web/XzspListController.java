package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IXzspListBO;
import com.ccs.util.Constants;
import com.ccs.util.EasyUiTree;
import com.ccs.vo.UserVO;
import com.ccs.vo.XzspListVO;
import com.ccs.web.domain.XzspListDomain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/xzsp.do")
public class XzspListController {
	
	
	@Autowired
	private IXzspListBO xzspListBO;

	@RequestMapping
	public String list(ModelMap model) throws Exception {
		
		return "xzsp/list";
	}

	
	@RequestMapping(params = "action=listAll", method = RequestMethod.GET)
	public @ResponseBody String listAll(ModelMap model) throws Exception {
		
		List<XzspListVO> list = xzspListBO.findAll();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);

		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}

	
	@RequestMapping(params = "action=search")
	public @ResponseBody String search(@RequestParam(value = "key", required = false) String key, ModelMap model) throws Exception {
		
		List<XzspListVO> list = xzspListBO.search(key);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);

		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=load")
	public @ResponseBody String load(@RequestParam(value = "key", required = false) String key, ModelMap model) throws Exception {
		
		List<XzspListVO> list = xzspListBO.findByListCode(key);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);

		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}
	

	@RequestMapping(params = "action=add")
	public String add(@ModelAttribute("xzspListDomain") XzspListDomain xzspListDomain, ModelMap model) {
		if (null == xzspListDomain) {
			xzspListDomain = new XzspListDomain();
		}
		model.addAttribute("xzspListDomain");

		return "xzsp/add";
	}

	@RequestMapping(params = "action=save")
	public String save(@ModelAttribute("xzspListDomain") XzspListDomain xzspListDomain, HttpSession session,
			ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		xzspListBO.save(xzspListDomain, user);
		return "redirect:xzsp.do";
	}
	
	
	@RequestMapping(params = "action=edit")
	public String edit(@RequestParam("pid") String pid, ModelMap model) throws Exception {
		
		XzspListVO vo = xzspListBO.findById(pid);
		
		XzspListDomain xzspListDomain = new XzspListDomain();
			xzspListDomain.setPid(vo.getPid());
			xzspListDomain.setListCode(vo.getListCode());
			xzspListDomain.setItemName(vo.getItemName());
			xzspListDomain.setCode(vo.getCode());
			xzspListDomain.setItemType(vo.getItemType());
			xzspListDomain.setTarget(vo.getTarget());
			xzspListDomain.setAccording(vo.getAccording());
			xzspListDomain.setRequirement(vo.getRequirement());
			xzspListDomain.setMaterials(vo.getMaterials());
			xzspListDomain.setProce(vo.getProce());
			xzspListDomain.setDealDept(vo.getDealDept());
			xzspListDomain.setLegalTerm(vo.getLegalTerm());
			xzspListDomain.setPromiseDate(vo.getPromiseDate());
			xzspListDomain.setChargeStand(vo.getChargeStand());
			xzspListDomain.setChargeAccording(vo.getChargeAccording());
			xzspListDomain.setDealPhone(vo.getDealPhone());
			xzspListDomain.setServicePhone(vo.getServicePhone());
			xzspListDomain.setEasyLevel(vo.getEasyLevel());
		model.addAttribute("xzspListDomain", xzspListDomain);

		return "xzsp/edit";
	}
	
	@RequestMapping(params = "action=editSave")
	public String editSave(@ModelAttribute("xzspListDomain") XzspListDomain xzspListDomain, HttpSession session,
			ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		xzspListBO.editSave(xzspListDomain, user);
		return "redirect:xzsp.do";
	}
	
	@RequestMapping(params = "action=del")
	public @ResponseBody void del(@RequestParam String pid, HttpSession session, ModelMap model) throws Exception {
		xzspListBO.del(pid);
	}
	
	
	
	@RequestMapping(params = "action=codetree", method = RequestMethod.GET)
	public @ResponseBody String buildListCodeTree() throws Exception {
		EasyUiTree tree = xzspListBO.buildListCodeTree();
		JSONArray jsonArray = JSONArray.fromObject(tree);
		return jsonArray.toString();
	}
	
	
	
	@RequestMapping(params = "action=view")
	public String view(@RequestParam("pid") String pid, ModelMap model) throws Exception {
		
		XzspListVO vo = xzspListBO.findById(pid);
		
		XzspListDomain xzspListDomain = new XzspListDomain();
			xzspListDomain.setPid(vo.getPid());
			xzspListDomain.setListCode(vo.getListCode());
			xzspListDomain.setItemName(vo.getItemName());
			xzspListDomain.setCode(vo.getCode());
			xzspListDomain.setItemType(vo.getItemType());
			xzspListDomain.setTarget(vo.getTarget());
			xzspListDomain.setAccording(vo.getAccording());
			xzspListDomain.setRequirement(vo.getRequirement());
			xzspListDomain.setMaterials(vo.getMaterials());
			xzspListDomain.setProce(vo.getProce());
			xzspListDomain.setDealDept(vo.getDealDept());
			xzspListDomain.setLegalTerm(vo.getLegalTerm());
			xzspListDomain.setPromiseDate(vo.getPromiseDate());
			xzspListDomain.setChargeStand(vo.getChargeStand());
			xzspListDomain.setChargeAccording(vo.getChargeAccording());
			xzspListDomain.setDealPhone(vo.getDealPhone());
			xzspListDomain.setServicePhone(vo.getServicePhone());
			xzspListDomain.setEasyLevel(vo.getEasyLevel());
		model.addAttribute("xzspListDomain", xzspListDomain);

		return "xzsp/view";
	}
}
