package com.lululu.O2O.util;

public class PathUtil {

	private static String seperator = System.getProperty("file.separator");
	
	public static String getImgBasePath() {
		
		// to avoid files being deleted when restarting the project
		// need to define the absolute path
		// and store files in the path out of the project
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/lululumi/Pictures";
		}
		basePath = basePath.replace("/", seperator);
		
		return basePath;
	}
	
	/**
	 * get shop image relative address
	 * each image is stored in a file based on shop id
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId) {
		
		String imagePath = "/upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
		
	}
}
