package com.ccs.dao;

import java.util.Map;

public interface IVolunteerSrvCountDAO {
	Map<String, String> getVolunteerSrvCount(String startDt, String endDt);
}
