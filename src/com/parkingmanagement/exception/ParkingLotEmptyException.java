package com.parkingmanagement.exception;

public class ParkingLotEmptyException extends Exception {

    public ParkingLotEmptyException() {
    }

    public ParkingLotEmptyException(String message) {
        super(message);
    }
}
