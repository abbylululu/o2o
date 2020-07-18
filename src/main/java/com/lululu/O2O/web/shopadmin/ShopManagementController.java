package com.lululu.O2O.web.shopadmin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lululu.O2O.dto.ShopExecution;
import com.lululu.O2O.entity.Area;
import com.lululu.O2O.entity.PersonInfo;
import com.lululu.O2O.entity.Shop;
import com.lululu.O2O.entity.ShopCategory;
import com.lululu.O2O.enums.ShopStateEnum;
import com.lululu.O2O.exceptions.ShopOperationException;
import com.lululu.O2O.service.AreaService;
import com.lululu.O2O.service.ShopCategoryService;
import com.lululu.O2O.service.ShopService;
import com.lululu.O2O.util.HttpServletRequestUtil;
import com.lululu.O2O.util.ImageUtil;
import com.lululu.O2O.util.PathUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopInitInfo() {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		} catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		
		return modelMap;
	}
	
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 1. receive and process params, including shop info and shop img
		// 1.1 process shop info
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch(Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		
		// 1.2 process shop img file stream
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "img shouldn't be empty");
			return modelMap;
		}
		
		// 2. register shop
		if (shop != null && shopImg != null) {
			
			// TODO: get user info from session
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			
			ShopExecution se;
			try {
				se = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
			return modelMap;
			
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "please insert shop info");
			return modelMap;
		}
		
	}
	
//	private static void inputStreamToFile(InputStream inputStream, File file) {
//		
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[1024];
//			while ((bytesRead = inputStream.read(buffer)) != -1) {
//				os.write(buffer, 0, bytesRead);
//			}
//		} catch(Exception e) {
//			throw new RuntimeException("error when calling inputStreamToFile: " + e.getMessage());
//		} finally {
//			try {
//				if (os != null) {
//					os.close();
//				}
//				if (inputStream != null) {
//					inputStream.close();
//				}
//			} catch(IOException e) {
//				throw new RuntimeException("error when closing io in calling inputStreamToFile" + e.getMessage());
//			}
//		}
//		
//	}
	
}
