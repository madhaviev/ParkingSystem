package com.madhu.test.parkingIntegrationTests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.madhu.parkingBeans.LevelInfo;
import com.madhu.parkingservice.LevelService;
import com.madhu.test.parkingbase.AbstractTestClass;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class ParkingLevelServiceTest extends AbstractTestClass {

	public static final Logger logger = Logger.getLogger(ParkingLevelServiceTest.class);

	private LevelService levelService;
	
	@Autowired
	public void setLevelService(LevelService ls) {
		
		this.levelService = ls;
	}
	
	@Test 
	public void createLevelTest() {
		
		logger.info("inside 1st test");
		assertEquals(1,1);
		
		LevelInfo levelObj = new LevelInfo();
		levelObj.setLevelNumber(50);
		levelObj.setCarSlotsCount(10);
		levelObj.setBikeSlotsCount(10);
		
		
		//logger.info("before createLevel");
		levelService.createLevel(levelObj);
		
		List<LevelInfo> levelObjList = levelService.getLevelsObjList();
		
		logger.info("levelObjList from DB is " + levelObjList);
		
		boolean containsLevel = levelObjList.contains(levelObj);

		logger.info("levelobj list printing " + levelObj + levelObjList.contains(levelObj));
		assertEquals(containsLevel, true);
		
		
	}

	}
