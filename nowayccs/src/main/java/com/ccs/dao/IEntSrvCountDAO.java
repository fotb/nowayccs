package com.ccs.dao;

import java.util.Map;

public interface IEntSrvCountDAO {
	Map<String, String> getEntSrvCount();
	
	Map<String, String> getEntSrvCountToday();
}
