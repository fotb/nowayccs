package com.ccs.web;

import java.util.ArrayList;
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

import com.ccs.bo.IBizLifeBO;
import com.ccs.bo.ILifeCategoryBO;
import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.util.Constants;
import com.ccs.util.EasyUiTree;
import com.ccs.util.LifeCategoryTreeUtil;
import com.ccs.util.Response;
import com.ccs.vo.LifeCategoryVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerStaffDomain;
import com.ccs.web.domain.PowerStaffListBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("lifecategory.do")
public class LifeCategoryController {

	@Autowired
	private IBizLifeBO bizLifeBO;

	@Autowired
	private ILifeCategoryBO lifeCategoryBO;

	@Autowired
	private ILightPowerStaffBO lpsBO;

	@RequestMapping(params = "action=list")
	public String list(ModelMap model) throws Exception {
		return "lifecategory/list";
	}

	@RequestMapping(params = "action=lifecategorytree")
	public @ResponseBody List<EasyUiTree> getLifeCategoryTree() throws Exception {
		List<LifeCategoryVO> list = bizLifeBO.getLifeCategory();
		LifeCategoryTreeUtil util = new LifeCategoryTreeUtil(list);
		EasyUiTree easyUiTree = util.generateEasyUiTree("0");
		List<EasyUiTree> tree = new ArrayList<EasyUiTree>();
		tree.add(easyUiTree);
		return tree;
	}

	@RequestMapping(params = "action=update")
	public @ResponseBody Response update(@RequestParam String code, @RequestParam String name, HttpSession session,
			ModelMap model) {
		Response res = new Response();
		try {
			LifeCategoryVO vo = lifeCategoryBO.findById(code);
			vo.setName(name);
			lifeCategoryBO.update(vo);
			res.success();
		} catch (Exception e) {
			res.failure(e.getMessage());
		}
		return res;
	}

	@RequestMapping(params = "action=delete")
	public @ResponseBody Response delete(@RequestParam String code, HttpSession session, ModelMap model)
			throws Exception {
		Response res = new Response();
		try {
			LifeCategoryVO vo = lifeCategoryBO.findById(code);
			lifeCategoryBO.delete(vo);
			res.success();
		} catch (Exception e) {
			res.failure(e.getMessage());
		}
		return res;
	}

	@RequestMapping(params = "action=add")
	public @ResponseBody Response add(@RequestParam String pcode, @RequestParam String name, @RequestParam String code,
			HttpSession session, ModelMap model) throws Exception {
		Response res = new Response();
		try {
			LifeCategoryVO vo = new LifeCategoryVO();
			vo.setPid(code);
			vo.setCode(code);
			vo.setName(name);
			vo.setParentCode(pcode);
			lifeCategoryBO.save(vo);;
			res.success();
		} catch (Exception e) {
			res.failure(e.getMessage());
		}
		return res;
	}

	@RequestMapping(params = "action=check")
	public @ResponseBody Response check(@RequestParam String code, HttpSession session, ModelMap model)
			throws Exception {
		Response res = new Response();
		try {
			List<LifeCategoryVO> voList = lifeCategoryBO.findByCode(code);
			if(voList.isEmpty() || voList.size() == 0) {
				res.success();
			} else {
				res.failure("code exist!");
			}
		} catch (Exception e) {
			res.failure(e.getMessage());
		}
		return res;
	}

}
