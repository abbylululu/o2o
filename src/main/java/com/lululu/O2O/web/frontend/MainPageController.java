package com.lululu.O2O.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lululu.O2O.entity.HeadLine;
import com.lululu.O2O.entity.ShopCategory;
import com.lululu.O2O.service.ShopCategoryService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private ShopCategoryService shopCategoryService;

	/**
	 * get the headline list, and first-level shop category
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listMainPageInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try {
			// get the first-level shop category
			shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		modelMap.put("success", true);
		return modelMap;
	}

}
