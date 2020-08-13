package com.lululu.O2O.service;

import java.io.IOException;
import java.util.List;

import com.lululu.O2O.entity.HeadLine;

public interface HeadLineService {
	public static final String HLLISTKEY = "headlinelist";

	List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
	
}
