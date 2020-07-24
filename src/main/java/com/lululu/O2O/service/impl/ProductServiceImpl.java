package com.lululu.O2O.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lululu.O2O.dao.ProductDao;
import com.lululu.O2O.dao.ProductImgDao;
import com.lululu.O2O.dto.ImageHolder;
import com.lululu.O2O.dto.ProductExecution;
import com.lululu.O2O.entity.Product;
import com.lululu.O2O.entity.ProductImg;
import com.lululu.O2O.enums.ProductStateEnum;
import com.lululu.O2O.exceptions.ProductOperationException;
import com.lululu.O2O.service.ProductService;
import com.lululu.O2O.util.ImageUtil;
import com.lululu.O2O.util.PageCalculator;
import com.lululu.O2O.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {

		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);

		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
		
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}

	@Override
	@Transactional
	// 1. process thumbnail, get the relative path and pass to product
	// 2. add to tb_product, and return product Id
	// 3. batch process product img by product id
	// 4. batch insert product img into tb_product_img
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// initialized as online
			product.setEnableStatus(1);
			// if thumbnail is not null, add to product info
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("Product creation failed");
				}
			} catch (Exception e) {
				throw new ProductOperationException("Product creation failed: " + e.toString());
			}
			// if product img is not null, batch insert into product info
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * add thumbnail helper function
	 * 
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	/**
	 * batch insert product img helper function
	 * 
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {

		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("Product img creation failed");
				}
			} catch (Exception e) {
				throw new ProductOperationException("Product img creation failed:" + e.toString());
			}
		}
		
	}

	/**
	 * delete product image by product id
	 * 
	 * @param productId
	 */
	private void deleteProductImgList(long productId) {

		// delete files
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}

		// delete data in db
		productImgDao.deleteProductImgByProductId(productId);
		
	}

	@Override
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList) throws ProductOperationException {
		// TODO Auto-generated method stub
		return null;
	}
}
