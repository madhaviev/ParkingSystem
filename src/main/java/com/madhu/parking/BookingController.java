package com.madhu.parking;

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

import com.madhu.parkingBeans.BookingInfo;
import com.madhu.parkingservice.BookingService;


@Controller
@RequestMapping(value="/bookingSlot")
public class BookingController {

	public static final Logger logger = Logger.getLogger(BookingController.class);
	
	BookingService bookingService;
	
	@Autowired
	public void setBookingService(BookingService bkngSrvc) {
		this.bookingService = bkngSrvc;
	}
	
	
	/**
	 * Mapper to book a slot for vehicle
	 * @param bookSlotInfo
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody BookingInfo createBookingSlot(@RequestBody BookingInfo bookSlotInfo) {
		
		return bookingService.createBookingSlot(bookSlotInfo);
		
	}
	
	
	/**
	 * Mapper to return the booking info for a vehicleId
	 * @param vehicleId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<BookingInfo> queryBookingSlot(@RequestParam("vehicleId") String vehicleId) {
		
		List<BookingInfo> bookingInfo = bookingService.searchBookingSlot(vehicleId, false);
		return bookingInfo;
	}
	
	
	/**
	 * Mapper to return all current booking slots
	 * @return
	 */
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<BookingInfo> queryAllCurrentBookingSlots() {
		
		List<BookingInfo> currentBookingSlots = bookingService.getCurrentBookingRecords();
		return currentBookingSlots;
	}
	
	
	/**
	 * Mapper to delete a booking slot
	 * @param bookingId
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteBookingSlot(@PathVariable("id") int bookingId) {
		bookingService.deleteBookingSlot(bookingId);
		
	}
}
