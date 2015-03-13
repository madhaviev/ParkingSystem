package com.madhu.parkingdao;

import java.util.List;

import com.madhu.parkingBeans.LevelInfo;

public interface LevelDao {
	
	
	/**
	 * Method to persist the Level information and create Slots
	 * @param levelInfo
	 */
	public void createLevel(LevelInfo levelInfo);

	
	/**
	 * Method to get Level information from slotsinfo table.
	 * @return
	 */
	public List<LevelInfo> getLevelObjList();

}
