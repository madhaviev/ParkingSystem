package com.madhu.test.parkingIntegrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.madhu.parkingBeans.BookingInfo;
import com.madhu.parkingBeans.LevelInfo;
import com.madhu.parkingBeans.SlotInfo;
import com.madhu.parkingBeans.SlotInfoDTO;
import com.madhu.parkingBeans.VehicleType;
import com.madhu.parkingservice.BookingService;
import com.madhu.parkingservice.LevelService;
import com.madhu.parkingservice.SlotService;
import com.madhu.test.parkingbase.AbstractTestClass;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class ParkingSlotServiceTest extends AbstractTestClass {
	
	public static final Logger logger = Logger.getLogger(ParkingSlotServiceTest.class);
	
	private SlotService slotService;
	
	private LevelService levelService;
	
	private BookingService bookingService;
	
	@Autowired
	public void setSlotService(SlotService slotSrvc) {
		this.slotService = slotSrvc;
	}
	
	@Autowired
	public void setLevelService(LevelService lsrvc) {
		this.levelService = lsrvc;
	}
	
	@Autowired
	public void setBookingService(BookingService bsrvc) {
		this.bookingService = bsrvc;
	}
	
	@Test
	public void getSlotsListTest() {
		
		LevelInfo lInfo = new LevelInfo();
		lInfo.setLevelNumber(10);
		lInfo.setCarSlotsCount(2);
		lInfo.setBikeSlotsCount(1);
		
		levelService.createLevel(lInfo);
		
		List<SlotInfoDTO> slotObjList = slotService.getSlotsList(10);
		
		assertEquals(slotObjList.size(),3);
		assertEquals(slotObjList.get(0).getLevelNumber(), 10);
		assertEquals(slotObjList.get(0).isAvailability(), true);
		assertEquals(slotObjList.get(1).isAvailability(), true);
		assertEquals(slotObjList.get(1).getLevelNumber(), 10);
		
	}

	public void searchForMatchingSlotIds(List<BookingInfo> currentBookingList, List<SlotInfo> freeSlotsList) {
		
		for(BookingInfo bObj : currentBookingList) {
			for(SlotInfo slotObj : freeSlotsList) {
				
				assertNotSame(bObj.getSlotObj().getSlotId(),slotObj.getSlotId() );
			}
			
		}
	}
	
	@Test
	public void getFreeSlotsInAllLevelsTest() {
		
			
		List<SlotInfo> freeSlotsCarList = slotService.getFreeSlotsInAllLevels(VehicleType.Car);
		
				
		List<BookingInfo> currentBookingList = bookingService.getCurrentBookingRecords();
		
		logger.info("current Booking List is " + currentBookingList);
		
		searchForMatchingSlotIds(currentBookingList, freeSlotsCarList);
		
		
		SlotInfo slotObjForBooking = new SlotInfo();
		slotObjForBooking.setSlotId(1);
		
		BookingInfo bookSlot = new BookingInfo();
		bookSlot.setVehicleId("aaa");
		bookSlot.setSlotObj(slotObjForBooking);
		Date today = new Date();
		bookSlot.setBookingTime(new Timestamp(today.getTime()));
		
		bookingService.createBookingSlot(bookSlot);
		
		freeSlotsCarList = slotService.getFreeSlotsInAllLevels(VehicleType.Car);
		
		currentBookingList = bookingService.getCurrentBookingRecords();
		
		logger.info("current Booking List now is " + currentBookingList);
		
		searchForMatchingSlotIds(currentBookingList, freeSlotsCarList);
		
	}
	
}
