package com.ccs.dao;

import java.util.Map;

public interface IVolunteerSrvCountDAO {
	Map<String, String> getVolunteerSrvCount();
	
	Map<String, String> getVolunteerSrvCountToday();
}
