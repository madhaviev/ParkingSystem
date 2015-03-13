package com.madhu.parkingBeans;


public class SlotInfoDTO {

	private int slotId;
	private String slotName;
	private int levelNumber;
	private VehicleType vehicleType;
	private boolean availability;
	
	public SlotInfoDTO() {
		
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
	
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	@Override
	public String toString() {
		return "SlotInfoDTO [slotId=" + slotId + ", slotName=" + slotName
				+ ", levelNumber=" + levelNumber + ", vehicleType="
				+ vehicleType + ", availability=" + availability + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (availability ? 1231 : 1237);
		result = prime * result + levelNumber;
		result = prime * result + slotId;
		result = prime * result
				+ ((slotName == null) ? 0 : slotName.hashCode());
		result = prime * result
				+ ((vehicleType == null) ? 0 : vehicleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		SlotInfoDTO other = (SlotInfoDTO) obj;
		if (availability != other.availability)
			return false;
		if (levelNumber != other.levelNumber)
			return false;
		if (slotId != other.slotId)
			return false;
		if (slotName == null) {
			if (other.slotName != null)
				return false;
		} else if (!slotName.equals(other.slotName))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
	
		return true;
	}

		
}
