package com.lululu.O2O.exceptions;

/**
 * extends RuntimeException is to ensure rollback
 * @author lululumi
 *
 */
public class ShopOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShopOperationException(String msg) {
		
		super(msg);
		
	}
	
}
