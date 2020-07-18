package com.lululu.O2O.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/*
 * 
 * wrap thumbnailator related methods
 */
public class ImageUtil {
	
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * transfer a Commons Multipart File into a File
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFiletoFile(CommonsMultipartFile cFile) {
		
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	 * process thumbnail and return relative path of the newly generated thumbnail
	 * @param thumbnail
	 * @param targetAddr: shop image relative path
	 * @return
	 */
	public static String generateThumbnail(File thumbnail, String targetAddr) {
		
		// need to avoid dup names, so create a relative address
		String realFileName = getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr is : " + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("current complete addr is: " + PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
			.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return relativeAddr;
		
	}
	
	/*
	 * current time + 5 digits of random number
	 * @return
	 */
	public static String getRandomFileName() {
		
		// 5 digits of random number
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		
		return nowTimeStr + rannum;
	}
	
	private static String getFileExtension(File cFile) {
		
		String originalFileName = cFile.getName();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
		
	}
	
	/*
	 * create directories in the target path
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		
	}
	
	// change the size of local img
	// add watermark to the img
	// suppress the img
	public static void main(String[] args) throws IOException {
		
		Thumbnails.of(new File("/Users/lululumi/Pictures/cloud.jpg")).size(200, 200)
		.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f)
		.toFile("/Users/lululumi/Pictures/cloud2.jpg");
		
	}
	
}
