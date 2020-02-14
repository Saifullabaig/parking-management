package com.parkingmanagement.exception;

public class ParkingSlotFullException extends Exception {

    public ParkingSlotFullException() {
    }

    public ParkingSlotFullException(String message) {
        super(message);
    }
}
