package com.madhu.test.parkingIntegrationTests;

import static org.junit.Assert.*;

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
import com.madhu.parkingservice.BookingService;
import com.madhu.parkingservice.LevelService;
import com.madhu.test.parkingbase.AbstractTestClass;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class ParkingBookingServiceTest extends AbstractTestClass{

	public static final Logger logger = Logger.getLogger(ParkingBookingServiceTest.class);
	
	private BookingService bookingService;
	
	private LevelService levelService;
	
	@Autowired
	public void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@Autowired
	public void setLevelService(LevelService lsrvc) {
		this.levelService = lsrvc;
	}
	
	@Test
	public void createBookingSlotTest() {
		
		LevelInfo lInfo = new LevelInfo();
		lInfo.setLevelNumber(10);
		lInfo.setCarSlotsCount(2);
		lInfo.setBikeSlotsCount(1);
		
		logger.info("before createLevel" + lInfo);
		
		levelService.createLevel(lInfo);
		
		logger.info("after createLevel");
		
		SlotInfo slotObjForBooking = new SlotInfo();
		slotObjForBooking.setSlotId(1);
		
		BookingInfo bookSlot = new BookingInfo();
		bookSlot.setVehicleId("abc");
		bookSlot.setSlotObj(slotObjForBooking);
		Date today = new Date();
		bookSlot.setBookingTime(new Timestamp(today.getTime()));
		
		bookingService.createBookingSlot(bookSlot);
		
		List<BookingInfo> searchObjList = bookingService.searchBookingSlot("abc", true);
		
		assertEquals(searchObjList.size(), 1);
		assertEquals(searchObjList.get(0).getVehicleId(), "abc");
		
		bookingService.deleteBookingSlot(searchObjList.get(0).getBookingId());
		
		List<BookingInfo> releaseBookingObjList = bookingService.searchBookingSlot("abc", true);
		
		logger.info("After releasing " + releaseBookingObjList);
		
		assertEquals(releaseBookingObjList.size(),	0);
		//assertNotNull(releaseBookingObjList.get(0).getReleaseTime());
		
	}
	
}
