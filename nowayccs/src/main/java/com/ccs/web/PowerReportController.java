package com.ccs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAreaBO;
import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.icd.util.DateUtil;
import com.ccs.util.StringUtil;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.web.domain.PowerInfoListBean;
import com.ccs.web.domain.PowerStaffReportBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("preport.do")
public class PowerReportController {

	@Autowired
	private ILightPowerStaffBO lpsBO;

	@Autowired
	private IAreaBO areaBO;

	@RequestMapping
	public String open(ModelMap model) throws Exception {
		return "power/report/reportlist";
	}

	@RequestMapping(params = "action=list", method = RequestMethod.GET)
	public @ResponseBody String list(@RequestParam(value = "areaId", required = false) String areaId,
			@RequestParam(value = "areaSubId", required = false) String areaSubId,
			@RequestParam(value = "startdt", required = false) String startDt,
			@RequestParam(value = "enddt", required = false) String endDt, ModelMap model) throws Exception {

		List<PowerStaffReportBean> beanList = lpsBO.powerStaffReport(areaId, areaSubId, startDt, endDt);

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", beanList.size());

		JSONArray jsonArray = JSONArray.fromObject(beanList);

		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}

	@RequestMapping(params = "action=export")
	public void export(@RequestParam(value = "areaId", required = false) String areaId,
			@RequestParam(value = "areaSubId", required = false) String areaSubId,
			@RequestParam(value = "startdt", required = false) String startDt,
			@RequestParam(value = "enddt", required = false) String endDt, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("state", null);

		String path = request.getSession().getServletContext().getRealPath("template");

		System.out.println(path);
		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		POIFSFileSystem fs = null;
		OutputStream fOut = null;

		// List<PowerStaffReportBean> beanList = lpsBO.powerStaffReport(areaId,
		// areaSubId, startDt, endDt);
		//
		// JSONObject jsonObj = new JSONObject();
		// jsonObj.put("total", beanList.size());
		//
		// JSONArray jsonArray = JSONArray.fromObject(beanList);
		//
		// jsonObj.put("rows", jsonArray.toString());
		HSSFWorkbook workbook = null;
		try {

			if ("\\".equals(File.separator)) { // windows
				fs = new POIFSFileSystem(new FileInputStream(path + "\\power_template.xls"));
			} else { // linux
				fs = new POIFSFileSystem(new FileInputStream(path + "/power_template.xls"));
			}
			// 进行转码，使其支持中文文件名
			codedFileName = "电工派单量统计报表";
			response.setHeader("content-disposition",
					"attachment;filename=" + new String(codedFileName.getBytes("UTF-8"), "ISO8859-1") + ".xls");
			// response.addHeader("Content-Disposition", "attachment; filename="
			// + codedFileName + ".xls");
			// 产生工作簿对象
			workbook = new HSSFWorkbook(fs);
			// 产生工作表对象
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow titleRow = sheet.getRow(0);
			titleRow.getCell(0).setCellValue(
					titleRow.getCell(0).getStringCellValue() + "(" + DateUtil.format(new Date(), "yyyy-MM-dd") + ")");

			titleRow = sheet.getRow(1);
			if (StringUtil.isNull(areaId)) {
				titleRow.getCell(1).setCellValue("全部区域");
			} else {
				AreaVO aVO = areaBO.findByAreaId(areaId);
				titleRow.getCell(1).setCellValue(aVO.getName());
			}

			if (StringUtil.isNull(areaSubId)) {
				titleRow.getCell(4).setCellValue("全部区域");
			} else {
				AreaSubVO asVO = areaBO.findByAreaSubId(areaSubId);
				titleRow.getCell(4).setCellValue(asVO.getName());
			}

			titleRow = sheet.getRow(2);
			titleRow.getCell(1).setCellValue(startDt + " - " + endDt);
			List<PowerStaffReportBean> beanList = lpsBO.powerStaffReport(areaId, areaSubId, startDt, endDt);
			for (int i = 0; i < beanList.size(); i++) {
				PowerStaffReportBean bean = beanList.get(i);
				HSSFRow row = sheet.createRow(i + 4);
				row.createCell(0).setCellValue(bean.getName());
				row.createCell(1).setCellValue(bean.getPhone());
				row.createCell(2).setCellValue(bean.getArea());
				row.createCell(3).setCellValue(bean.getAreaSub());
				row.createCell(4).setCellValue(bean.getCount());
				row.createCell(5).setCellValue(bean.getTodayCount());
			}

			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				fOut.flush();
				fOut.close();
				fs.close();
			} catch (IOException e) {
			}
			session.setAttribute("state", "open");
		}
		System.out.println("文件生成...");
	}

	@RequestMapping(params = "action=infolist")
	public @ResponseBody String infolist(@RequestParam(value = "infoStartDt", required = false) String startDt,
			@RequestParam(value = "infoEndDt", required = false) String endDt,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows) throws Exception {
		
		
		List<PowerInfoListBean> list = lpsBO.queryPowerInfo(startDt, endDt, Integer.valueOf(page), Integer.valueOf(rows));
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", lpsBO.queryPowerInfoCount(startDt, endDt));
		JSONArray jsonArray = JSONArray.fromObject(list);

		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}

	
	
	
	@RequestMapping(params = "action=exportpowerinfo")
	public void exportPowerInfo(@RequestParam(value = "infoStartDt", required = false) String infoStartDt,
			@RequestParam(value = "infoEndDt", required = false) String infoEndDt, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {

		HttpSession session = request.getSession();
		session.setAttribute("state", null);

		String path = request.getSession().getServletContext().getRealPath("template");

		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		POIFSFileSystem fs = null;
		OutputStream fOut = null;

		// List<PowerStaffReportBean> beanList = lpsBO.powerStaffReport(areaId,
		// areaSubId, startDt, endDt);
		//
		// JSONObject jsonObj = new JSONObject();
		// jsonObj.put("total", beanList.size());
		//
		// JSONArray jsonArray = JSONArray.fromObject(beanList);
		//
		// jsonObj.put("rows", jsonArray.toString());
		HSSFWorkbook workbook = null;
		try {

			if ("\\".equals(File.separator)) { // windows
				fs = new POIFSFileSystem(new FileInputStream(path + "\\power_info_list.xls"));
			} else { // linux
				fs = new POIFSFileSystem(new FileInputStream(path + "/power_info_list.xls"));
			}
			// 进行转码，使其支持中文文件名
			codedFileName = "电力服务派单详细表";
			response.setHeader("content-disposition",
					"attachment;filename=" + new String(codedFileName.getBytes("UTF-8"), "ISO8859-1") + ".xls");
			// response.addHeader("Content-Disposition", "attachment; filename="
			// + codedFileName + ".xls");
			// 产生工作簿对象
			workbook = new HSSFWorkbook(fs);
			// 产生工作表对象
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow titleRow = sheet.getRow(0);
			titleRow.getCell(0).setCellValue(
					titleRow.getCell(0).getStringCellValue() + "(" + DateUtil.format(new Date(), "yyyy-MM-dd") + ")");
			
			titleRow = sheet.getRow(1);
			titleRow.getCell(0).setCellValue(infoStartDt + " ---- " + infoEndDt);
			
			List<PowerInfoListBean> beanList = lpsBO.queryPowerInfo(infoStartDt, infoEndDt);
			for (int i = 0; i < beanList.size(); i++) {
				PowerInfoListBean bean = beanList.get(i);
				HSSFRow row = sheet.createRow(i + 3);
				row.createCell(0).setCellValue(bean.getHelpName());
				row.createCell(1).setCellValue(bean.getHelpMode());
				row.createCell(2).setCellValue(bean.getHelpTel());
				row.createCell(3).setCellValue(bean.getHelpAddr());
				row.createCell(4).setCellValue(bean.getHelpContent());
				row.createCell(5).setCellValue(bean.getPname());
				row.createCell(6).setCellValue(bean.getPphone());
				row.createCell(7).setCellValue(bean.getArea());
				row.createCell(8).setCellValue(bean.getUserName());
				row.createCell(9).setCellValue(bean.getCreateDt());
			}

			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (UnsupportedEncodingException e1) {
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
				fOut.flush();
				fOut.close();
				fs.close();
			} catch (IOException e) {
			}
			session.setAttribute("state", "open");
		}
		System.out.println("文件生成...");
	}
	
	
	@RequestMapping(params = "action=fix")
	public String associate(ModelMap model) throws Exception {
		lpsBO.fix();
		return "";
	}
}
