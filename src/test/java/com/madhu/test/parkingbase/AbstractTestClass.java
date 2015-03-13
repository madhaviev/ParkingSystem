package com.madhu.test.parkingbase;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations= {
		
		"classpath:com/madhu/parkingtestconfigs/parking-testDataSource.xml",
		"classpath:com/madhu/parkingtestconfigs/parking-testFlyway.xml",
		"classpath:META-INF/spring/parking-context.xml",
		"classpath:META-INF/spring/parking-sessionFactory.xml"
		
})

public abstract class AbstractTestClass extends AbstractJUnit4SpringContextTests { 

}
