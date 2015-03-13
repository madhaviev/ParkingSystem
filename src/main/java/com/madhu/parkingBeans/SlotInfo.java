package com.madhu.parkingBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SLOTSINFO")
public class SlotInfo {
	
	@Id
	@SequenceGenerator(name="sequence", sequenceName="SLOTID_SEQUENCE", allocationSize=1)
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	@Column(name="ID")
	private int slotId;
	
	@Column(name="SLOTNAME")
	private String slotName;
	
	@Column(name="LEVELNUMBER")
	private int levelNumber;
	
	@Column(name="VEHICLETYPE")
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	
	public SlotInfo() {
		
	}
	
	public int getSlotId() {
		return slotId;
	}
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "SlotInfo [slotId=" + slotId + ", slotName=" + slotName
				+ ", levelNumber=" + levelNumber + ", vehicleType="
				+ vehicleType + "]";
	}
	
	
}
