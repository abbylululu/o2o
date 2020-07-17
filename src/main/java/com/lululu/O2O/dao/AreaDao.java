package com.lululu.O2O.dao;

import java.util.List;

import com.lululu.O2O.entity.Area;

public interface AreaDao {
	
	/*
	 * list all areas
	 * @return areaList
	 */
	List<Area> queryArea();
	
}
