package com.lululu.O2O.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lululu.O2O.cache.JedisUtil;
import com.lululu.O2O.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private JedisUtil.Keys jedisKeys;
	
	@Override
	public void removeFromCache(String keyPrefix) {
		Set<String> keySet = jedisKeys.keys(keyPrefix + "*");
		for (String key: keySet) {
			jedisKeys.del(key);
		}
	}
	
}
