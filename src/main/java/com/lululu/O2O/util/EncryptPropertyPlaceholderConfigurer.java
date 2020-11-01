package com.lululu.O2O.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	// list of properties needed for encryption
	private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// decrypt the encrypted properties
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtil.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}
	
	private boolean isEncryptProp(String propertyName) {
		for (String encryptPropertyName: encryptPropNames) {
			if (encryptPropertyName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
