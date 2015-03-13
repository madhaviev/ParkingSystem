package com.madhu.parkingdao;

import java.util.List;

import com.madhu.parkingBeans.SlotInfo;
import com.madhu.parkingBeans.SlotInfoDTO;
import com.madhu.parkingBeans.VehicleType;

public interface SlotDao {

	
	/**
	 * Method to get the list of slot objects for given levelnumber
	 * @param levelNumber
	 * @return
	 */
	List<SlotInfoDTO> getSlotsList(int levelNumber);

	
	/**
	 * Method to get all free slots in all levels
	 * @param vehicleType
	 * @return
	 */
	List<SlotInfo> getFreeSlotsInAllLevels(VehicleType vehicleType);


	/**
	 * Method to delete slot
	 * @param slotId
	 */
	void deleteSlot(int slotId);
	

}
