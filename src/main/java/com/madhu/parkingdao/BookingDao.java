package com.madhu.parkingdao;

import java.util.List;

import com.madhu.parkingBeans.BookingInfo;
import com.madhu.parkingBeans.SlotInfo;

public interface BookingDao {

	
	/**
	 * Book a slot for vehicleId. Search if already booking slot exists for the vehicle.
	 * @param bookSlotInfo
	 * @return
	 */
	BookingInfo createBookingSlot(BookingInfo bookSlotInfo);

	
	/**
	 * Search the booking for the given vehicleId. Boolean used to perform the cmplt match or partial match of vehicleId
	 * @param vehicleId
	 * @param performCmpltMatch
	 * @return
	 */
	List<BookingInfo> searchBookingSlot(String vehicleId, boolean performCmpltMatch);

	
	/**
	 * Mark the booking slot as released for the vehicle
	 * @param bookingId
	 */
	void releaseBookingSlot(int bookingId);

	
	/**
	 * Retrieve all current booking records
	 * @return
	 */
	List<BookingInfo> getCurrentBookingRecords();
	
	/**
	 * Delete the booking slot
	 * @param slotInfo
	 */
	void deleteBookingSlot(SlotInfo slotInfo);

}
