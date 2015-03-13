package com.madhu.parkingBeans;

public enum VehicleType {
	
	Car, Bike;
	
	public String toString() {
		switch(this) {
			case Car : 
				return "Car";
			case Bike : 
				return "Bike";
		}
		return null;
	}

}
