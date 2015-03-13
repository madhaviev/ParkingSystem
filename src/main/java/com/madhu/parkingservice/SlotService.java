package com.madhu.parkingservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madhu.parkingBeans.SlotInfo;
import com.madhu.parkingBeans.SlotInfoDTO;
import com.madhu.parkingBeans.VehicleType;
import com.madhu.parkingdao.SlotDao;

@Service
@Transactional
public class SlotService {

	SlotDao slotIntf;
	
	@Autowired
	public void setSlotIntf(SlotDao slotIntfVal) {
		this.slotIntf = slotIntfVal;
	}
	
	public static final Logger logger = Logger.getLogger(SlotService.class);

	public List<SlotInfoDTO> getSlotsList(int levelNumber) {
		
		List<SlotInfoDTO> slotObjList = slotIntf.getSlotsList(levelNumber);
		return slotObjList;
	}
	

	public List<SlotInfo> getFreeSlotsInAllLevels(VehicleType vehicleType) {
		
		List<SlotInfo> freeSlotsInLevelsObj = slotIntf.getFreeSlotsInAllLevels(vehicleType);
		return freeSlotsInLevelsObj;
	}


	public void deleteSlot(int slotId) {
		slotIntf.deleteSlot(slotId);
		
	}
	
	
}
