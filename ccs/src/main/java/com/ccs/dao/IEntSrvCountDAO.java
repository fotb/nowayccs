package com.ccs.dao;

import java.util.Map;

public interface IEntSrvCountDAO {
	Map<String, String> getEntSrvCount(String startDt, String endDt, String creator);
}
