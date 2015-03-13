package com.madhu.parkingservice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madhu.parkingBeans.LevelInfo;
import com.madhu.parkingdao.LevelDao;

@Service
@Transactional
public class LevelService {
	
	LevelDao levelIntf;
	
	public static final Logger logger = Logger.getLogger(LevelService.class);
	
	@Autowired
	public void setLevelIntf(LevelDao lDaoIntf) {
		levelIntf  = lDaoIntf;
	}
	
	public void createLevel(LevelInfo levelInfo) {
		logger.info("Inside createLevel service method");
		levelIntf.createLevel(levelInfo);
	}

	public List<LevelInfo> getLevelsObjList() {
		List<LevelInfo> levelObjList = new ArrayList<LevelInfo>();
		
		levelObjList = levelIntf.getLevelObjList();
		
		return levelObjList;
		
	}

	
}
