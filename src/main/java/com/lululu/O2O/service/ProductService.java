package com.lululu.O2O.service;

import java.util.List;

import com.lululu.O2O.dto.ImageHolder;
import com.lululu.O2O.dto.ProductExecution;
import com.lululu.O2O.entity.Product;
import com.lululu.O2O.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * get product list with pagination, conditions are: product name(fuzzy), product state, shop id, product category
	 * 
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

	/**
	 * query product info by product is
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);

	/**
	 * add new product with product img
	 * 应该对img name & img file 进行封装
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException;

	/**
	 * modify product and process img
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException;
}
