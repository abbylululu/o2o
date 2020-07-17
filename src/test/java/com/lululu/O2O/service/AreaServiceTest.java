package com.lululu.O2O.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lululu.O2O.BaseTest;
import com.lululu.O2O.entity.Area;

public class AreaServiceTest extends BaseTest {
	
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetAreaList() {
		
		List<Area> areaList = areaService.getAreaList();
		assertEquals("南苑", areaList.get(0).getAreaName());
	}
	
}
