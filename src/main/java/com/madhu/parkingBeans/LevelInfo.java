package com.madhu.parkingBeans;

public class LevelInfo {

	int levelNumber;
	int carSlotsCount;
	int bikeSlotsCount;
	
	public LevelInfo() {
		
	}
	
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	
	public void setCarSlotsCount(int carSlotsCount) {
		this.carSlotsCount = carSlotsCount;
	}

	public void setBikeSlotsCount(int bikeSlotsCount) {
		this.bikeSlotsCount = bikeSlotsCount;
	}
	
	public int getBikeSlotsCount() {
		return bikeSlotsCount;
	}
	
	public int getLevelNumber() {
		return levelNumber;
	}

	public int getCarSlotsCount() {
		return carSlotsCount;
	}

	@Override
	public String toString() {
		return "LevelInfo [levelNumber=" + levelNumber + ", carSlotsCount="
				+ carSlotsCount + ", bikeSlotsCount=" + bikeSlotsCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bikeSlotsCount;
		result = prime * result + carSlotsCount;
		result = prime * result + levelNumber;
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
		
		LevelInfo other = (LevelInfo) obj;
		if (bikeSlotsCount != other.bikeSlotsCount)
			return false;
		if (carSlotsCount != other.carSlotsCount)
			return false;
		if (levelNumber != other.levelNumber)
			return false;
		return true;
	}

}
