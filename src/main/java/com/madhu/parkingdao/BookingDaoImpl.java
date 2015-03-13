package com.madhu.parkingdao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import com.madhu.parkingBeans.BookingInfo;
import com.madhu.parkingBeans.SlotInfo;

@Repository
public class BookingDaoImpl extends BaseHibernateDao<BookingInfo, Integer> implements BookingDao {

	public static final Logger logger = Logger.getLogger(BookingDaoImpl.class);
	
	public BookingDaoImpl() {
		super(BookingInfo.class);
	}

	//Returns the current Time stamp
	public Timestamp getCurrentTimeStamp() {
	
		Date today = new Date();
		return new Timestamp(today.getTime());
	}	

	
	@Override
	public BookingInfo createBookingSlot(BookingInfo bookSlotInfo) {
		
		List<BookingInfo> searchBookingSlot = searchBookingSlot(bookSlotInfo.getVehicleId(), true);
		
		if(!searchBookingSlot.isEmpty()) {
			bookSlotInfo.setErrorMsg("Booking already exists for the vehicle");
			return bookSlotInfo;
		}

		bookSlotInfo.setBookingTime(getCurrentTimeStamp());
		return makePersistant(bookSlotInfo);
	}

 
	@Override
	public List<BookingInfo> searchBookingSlot(String vehicleId, boolean performCmpltMatch) {
		
		Criteria cr = getSession().createCriteria(BookingInfo.class);
		
		SimpleExpression cmpltMatchRestriction = performCmpltMatch ? Restrictions.eq("vehicleId", vehicleId).ignoreCase() : Restrictions.like("vehicleId", vehicleId).ignoreCase();
		
		List<BookingInfo> bookingInfoList = cr.add(cmpltMatchRestriction)
											  .add(Restrictions.isNull("releaseTime"))
											  .list();
		return bookingInfoList;
	}

	
	@Override
	public void releaseBookingSlot(int bookingId) {
		
		BookingInfo bookingInfoToRetrieve = findById(bookingId);
		bookingInfoToRetrieve.setReleaseTime(getCurrentTimeStamp());
		
	}

	
	@Override
	public List<BookingInfo> getCurrentBookingRecords() {
		
		Criteria cr = getSession().createCriteria(BookingInfo.class);
		
		List<BookingInfo> bookingInfoList = cr.add(Restrictions.isNull("releaseTime"))
											  .list();
					
		return bookingInfoList;
	}
	
	@Override
	public void deleteBookingSlot(SlotInfo slotObj) {
		
		Criteria cr = getSession().createCriteria(BookingInfo.class);
		
		List<BookingInfo> bookingInfoList = cr.add(Restrictions.eq("slotObj", slotObj))
											  .list();
		for(BookingInfo b : bookingInfoList) {
			makeTransient(b);
		}
											  
	}

}
