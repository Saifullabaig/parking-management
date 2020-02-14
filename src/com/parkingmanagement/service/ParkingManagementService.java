package com.parkingmanagement.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.parkingmanagement.exception.CarNotFoundException;
import com.parkingmanagement.exception.ParkingLotEmptyException;
import com.parkingmanagement.exception.ParkingSlotFullException;
import com.parkingmanagement.model.ParkedCars;

public class ParkingManagementService {

	public static List<ParkedCars> parkingList = new ArrayList<ParkedCars>();
	InputStreamReader r = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(r);
	public static int slotSize;
	public boolean[] parkingStatus = new boolean[slotSize];
	public static int slotNum;

	// 1. Registration Function
	public void registration() throws ParkingSlotFullException {
		try {
			if (parkingList.size() < slotSize) {

				System.out.println("Enter car number:");
				String number = br.readLine().toLowerCase();
				System.out.println("Enter car colour:");
				String colour = br.readLine().toLowerCase();

				ParkedCars parkedcar = new ParkedCars(number, colour, getSlot());
				parkingList.add(parkedcar);
				updateStatus(getSlot(), true);
				System.out.println(parkedcar);

			} else {
				throw new ParkingSlotFullException("Sorry, parking lot is full");
			}
		} catch (Exception ee) {
			throw new ParkingSlotFullException("Sorry, parking lot is full");
		}
	}

	public int getSlot() {
//		System.out.println("sloztsize"+slotSize);
		for (int i = 0; i < slotSize; i++) {
			if (!parkingStatus[i]) {
				return i + 1;
			}
		}
		return 0;
	}

	public void updateStatus(int slot, boolean status) {
		parkingStatus[slot - 1] = status;
	}

	// 2. remove the car from arraylist after pickup
	public void pickUp(String carNum) throws CarNotFoundException, ParkingLotEmptyException {
		try {
			if (parkingList.size() > 0) {
				ParkedCars pCars = new ParkedCars();
				for (ParkedCars parkedCars : parkingList) {
					if (parkedCars.getNumberPlate().equals(carNum)) {
						pCars = parkedCars;
					}
				}
				if (pCars.getNumberPlate() != null) {
					updateStatus(pCars.getSlotNumber(), false);
					parkingList.remove(pCars);
					System.out.println("Leave " + pCars.getSlotNumber());
				} else {
					throw new CarNotFoundException("Sorry!! No car found in lobby");
				}
			} else {
				throw new ParkingLotEmptyException("Oops there is no car parking in the lobby");
			}
		} catch (CarNotFoundException ee) {
			throw new CarNotFoundException(ee.getMessage());
		} catch (ParkingLotEmptyException pe) {
			throw new ParkingLotEmptyException(pe.getMessage());
		}

	}

	// 3. get Status of parking lot
	public void parkingStatus() {
		System.out.println("Parking lot status..............................");
		System.out.println("Slot No." + " " + "Car Number" + "    " + "Colour");
		for (ParkedCars parkedCars : parkingList) {
			System.out.println(parkedCars.getSlotNumber() + "        " + parkedCars.getNumberPlate() + "    "
					+ parkedCars.getCarColour());
		}
		for (int i = 0; i < slotSize; i++) {
			if (!parkingStatus[i]) {
				System.out.println("Slot number " + (i + 1) + " is empty");
			}
		}
	}
	// 4. get slot number by giving car number

	public void getSlotByCarNumber(String carNumber) throws CarNotFoundException,NullPointerException {
		try {
			ParkedCars parkedCar = new ParkedCars();
			for (ParkedCars parkedCars : parkingList) {
				if (parkedCars.getNumberPlate().equals(carNumber)) {
					parkedCar = parkedCars;
				}
			}
			if (!parkedCar.getNumberPlate().isEmpty()) {
				System.out.println("Car is parked in slot number " + parkedCar.getSlotNumber());
			} else {
				throw new CarNotFoundException("No Car with this number was found in parking lot");
			}
		} catch (CarNotFoundException ee) {
			throw new CarNotFoundException(ee.getMessage());
		}
		catch (Exception ee){
			throw new NullPointerException("No Car with this number was found in parking lot");
		}
	}

	// 5. get car numbers of given colour
	public void getCarsByColour(String carColour) throws CarNotFoundException {
		try {
			List<ParkedCars> parkedCars = parkingList.stream().filter(pCar -> pCar.getCarColour().equals(carColour))
					.collect(Collectors.toList());
			if (!parkedCars.isEmpty()) {
				System.out.println("Cars with colour " + carColour + " are ");
				for (ParkedCars parkedCar : parkedCars) {
					System.out.println(parkedCar.getNumberPlate());
				}
			} else {
				throw new CarNotFoundException("No Car with this colour was found in parking lot");
			}
		} catch (CarNotFoundException ee) {
			throw new CarNotFoundException(ee.getMessage());
		}
	}

	//6. slots by colour of car
	public void getSlotsByCarColour(String carColour) throws CarNotFoundException, NullPointerException{
		try {
			List<ParkedCars> parkedCars = parkingList.stream().filter(pCar -> pCar.getCarColour().equals(carColour))
					.collect(Collectors.toList());
			if (!parkedCars.isEmpty()) {
				System.out.println("Slots with Cars colour " + carColour + " are ");
				for (ParkedCars parkedCar : parkedCars) {
					System.out.println(parkedCar.getSlotNumber());
				}
			} else {
				throw new CarNotFoundException("No Car with this colour was found in parking lot");
			}
		} catch (CarNotFoundException ee) {
			throw new CarNotFoundException(ee.getMessage());
		} catch (NullPointerException nul){
			throw new NullPointerException("No car colours");
		}
	}
}