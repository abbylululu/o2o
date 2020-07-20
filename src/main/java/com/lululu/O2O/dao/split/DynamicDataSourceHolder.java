package com.lululu.O2O.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicDataSourceHolder {

	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
	private static ThreadLocal<String> contextHolder = new ThreadLocal<>(); // ensure thread safety
	public static final String DB_MASTER = "master";
	public static final String DB_SLAVE = "slave";
	public static String getDbType() {
		
		String db = contextHolder.get();
		if (db == null) {
			db = DB_MASTER;
		}
		return db;
		
	}
	
	/**
	 * set dbType
	 * @param str
	 */
	public static void setDbType(String str) {
		
		logger.debug("所使用的数据源为: " + str);
		contextHolder.set(str);
		
	}
	
	/**
	 * clear connectiont type
	 */
	public static void clearDbType() {
		
		contextHolder.remove();
		
	}
	
}
