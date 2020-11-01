package com.lululu.O2O;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * 
 * for spring junit configuration
 * start junit and load spring IOC container
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// tell JUnit spring config file's location
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-redis.xml"})
public class BaseTest {

}
