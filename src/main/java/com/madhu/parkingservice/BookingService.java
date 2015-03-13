package com.madhu.parkingservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.madhu.parkingBeans.BookingInfo;
import com.madhu.parkingdao.BookingDao;

@Service
@Transactional
public class BookingService {

	BookingDao bookingDao;
	
	public static final Logger logger = Logger.getLogger(BookingService.class);
	
	@Autowired
	public void setBookingServiceIntf(BookingDao b) {
		this.bookingDao = b;
	}
	
	public BookingInfo createBookingSlot(BookingInfo bookSlotInfo) {
		return bookingDao.createBookingSlot(bookSlotInfo);
		
	}

	public List<BookingInfo> searchBookingSlot(String vehicleId, boolean performCmpltMatch) {
		return bookingDao.searchBookingSlot(vehicleId,performCmpltMatch);
	}

	public void deleteBookingSlot(int bookingId) {
	
	   bookingDao.releaseBookingSlot(bookingId);	
	}
	
	public List<BookingInfo> getCurrentBookingRecords() {
		return bookingDao.getCurrentBookingRecords();
	}

	
	
}
