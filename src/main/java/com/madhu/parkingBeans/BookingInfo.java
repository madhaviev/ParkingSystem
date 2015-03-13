package com.madhu.parkingBeans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="BOOKINGINFO")
public class BookingInfo {

	public BookingInfo() {
		
	}
	
	@Id
	@SequenceGenerator(name="sequence", sequenceName="BOOKINGID_SEQUENCE", allocationSize=1)
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private int bookingId;
	
	@Column(name="VEHICLEID")
	private String vehicleId;
	
	@OneToOne(fetch=FetchType.EAGER )
	@JoinColumn(name="slotId")
	private SlotInfo slotObj;
	
	@Column(name="BOOkINGTIME")
	private Timestamp bookingTime;
	
	@Column(name="RELEASETIME")
	private Timestamp releaseTime;
	
	@Transient
	private String errorMsg;

	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public SlotInfo getSlotObj() {
		return slotObj;
	}

	public void setSlotObj(SlotInfo slotObj) {
		this.slotObj = slotObj;
	}

	public Timestamp getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Timestamp bookingTime) {
		this.bookingTime = bookingTime;
	}

	public Timestamp getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Override
	public String toString() {
		return "BookingInfo [bookingId=" + bookingId + ", vehicleId="
				+ vehicleId + ", slotObj=" + slotObj + ", bookingTime="
				+ bookingTime + ", releaseTime=" + releaseTime + ", errorMsg="
				+ errorMsg + "]";
	}
	
	
}
