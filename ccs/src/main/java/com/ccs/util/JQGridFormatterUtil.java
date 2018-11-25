package com.ccs.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

public class JQGridFormatterUtil {
	private static final Logger logger = Logger.getLogger(JQGridFormatterUtil.class);

	@SuppressWarnings("rawtypes")
	public static String getJSON(int currentPageNo, int totalRecords, int rows, 
			List objectsToBeAdded, List<String> orderedPropertyNames, String idKey) {

		Integer pages = 0;

		if (totalRecords % rows > 0) {
			pages = (totalRecords / rows) + 1;
		} else {
			pages = (totalRecords / rows);
		}

		JQGridContainer container = new JQGridContainer();
		container.setPage(currentPageNo);
		container.setTotal(pages);
		container.setRecords(totalRecords);
		List<JQGridRow> rowList = new ArrayList<JQGridRow>();
		if (!objectsToBeAdded.isEmpty()) {
			for (Object obj : objectsToBeAdded) {
				JQGridRow row = new JQGridRow();
				row.setId(getPropertvalue(obj, idKey));
				List<String> cells = new ArrayList<String>();
				for (String propertyName : orderedPropertyNames) {
					cells.add(getPropertvalue(obj, propertyName));
				}
				row.setCell(cells);
				rowList.add(row);
			}
		}
		container.setRows(rowList);
		JSONObject json = JSONObject.fromObject(container);
		return json.toString();
	}

	private static String getPropertvalue(Object bean, String propName) {
		String val = null;
		try {
			val = ObjectUtils.toString(PropertyUtils.getProperty(bean, propName));
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		} catch (NoSuchMethodException e) {
			logger.error(e);
		}
		return val;
	}

}
