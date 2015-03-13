package com.madhu.parking;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.madhu.parkingBeans.LevelInfo;
import com.madhu.parkingBeans.SlotInfo;
import com.madhu.parkingBeans.SlotInfoDTO;
import com.madhu.parkingBeans.VehicleType;
import com.madhu.parkingservice.LevelService;
import com.madhu.parkingservice.SlotService;

/**
 * @author madhavi
 *
 */
@Controller
@RequestMapping(value="/garage/")
public class ManageGarageController {

	public static final Logger logger = Logger.getLogger(ManageGarageController.class);
	
	LevelService levelService;
	SlotService slotService;
	
	@Autowired
	public void setLevelService(LevelService lservice) {
		this.levelService = lservice;
	}
	
	@Autowired
	public void setSlotService(SlotService slotSrvc) {
		this.slotService = slotSrvc;
	}
	
	
	/**
	 * Mapper to create new Level
	 * @param levelInfo
	 * @return
	 */
	@RequestMapping(value="level", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public  @ResponseBody LevelInfo createLevel(@RequestBody LevelInfo levelInfo) {
		
		levelService.createLevel(levelInfo);
		return levelInfo;
	}
	
	
	/**
	 * Mapper to get the list of all Levels
	 * @return
	 */
	@RequestMapping(value="level", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<LevelInfo> getLevelsList() {
		
		List<LevelInfo> levelList = new ArrayList<LevelInfo>();
		levelList = levelService.getLevelsObjList();
		return levelList;
	}
	
	
	/**
	 * Mapper to get the list of all current Free levels
	 * @param vehicleType
	 * @return
	 */
	@RequestMapping(value="freeLevels", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<SlotInfo> getFreeSlotsInAllLevels(@RequestParam("vehicleType") VehicleType vehicleType) {
		
		List<SlotInfo> levelsAndSlotsList = slotService.getFreeSlotsInAllLevels(vehicleType);
		return levelsAndSlotsList;
	}
	
	
	/**
	 * Mapper to get list of all slots of a levelnumber
	 * @param levelNumber
	 * @return
	 */
	@RequestMapping(value="level/slots/{levelNumber}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<SlotInfoDTO> getSlotsList(@PathVariable("levelNumber") int levelNumber) {
		
		
		List<SlotInfoDTO> slotObjList = slotService.getSlotsList(levelNumber);
			
		return slotObjList;
	}
	
	/**
	 * Mapper to delete slot 
	 * @param slotId
	 */
	@RequestMapping(value="slot/{slotId}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody void deleteSlot(@PathVariable("slotId") int slotId) {
		
		logger.info("Inside deleteSlot Controller mapper method");
		slotService.deleteSlot(slotId);
		
	}
}
