package com.parkingmanagement.model;

public class ParkedCars {

	private String numberPlate;
	private String carColour;
	private int slotNumber;
	public ParkedCars() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParkedCars(String numberPlate, String carColour, int slotNumber) {
		super();
		this.numberPlate = numberPlate;
		this.carColour = carColour;
		this.slotNumber = slotNumber;
	}
	public String getNumberPlate() {
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public String getCarColour() {
		return carColour;
	}
	public void setCarColour(String carColour) {
		this.carColour = carColour;
	}
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	@Override
	public String toString() {
		return "ParkedCar:"+"\n"+
				"Car Number=" + numberPlate +"\n"+ 
				"Car Colour=" + carColour +"\n"+ 
				"Slot Number=" + slotNumber;
	}
	
	
}
