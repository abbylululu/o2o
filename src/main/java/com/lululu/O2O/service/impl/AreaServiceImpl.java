package com.lululu.O2O.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lululu.O2O.dao.AreaDao;
import com.lululu.O2O.entity.Area;
import com.lululu.O2O.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getAreaList() {
		
		return areaDao.queryArea();
		
	}
	
}
