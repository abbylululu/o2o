package com.lululu.O2O.service;

import java.util.List;

import com.lululu.O2O.entity.Area;

public interface AreaService {
	public static final String AREALISTKEY = "arealist";
	
	List<Area> getAreaList();
	
}
