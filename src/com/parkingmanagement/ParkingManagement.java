package com.parkingmanagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.parkingmanagement.exception.CarNotFoundException;
import com.parkingmanagement.exception.ParkingLotEmptyException;
import com.parkingmanagement.exception.ParkingSlotFullException;
import com.parkingmanagement.service.ParkingManagementService;

public class ParkingManagement {
    public static List<Integer> allEvents;
    public static int slotList;

    public static void main(String[] args) throws Exception {

        allEvents = new ArrayList<Integer>();
        slotRegistration();
        Scanner scan = new Scanner(System.in);

        ParkingManagementService managementService = new ParkingManagementService();
        int token = 0;
        while (true) {
            System.out.println(".................................");
            System.out.println("Enter 1 to park your car");
            System.out.println("Enter 2 to pick your car");
            System.out.println("Enter 3 to see the status of parking lot");
            System.out.println("Enter 4 to see the slot of your car");
            System.out.println("Enter 5 to see the number of cars with respective colour");
            System.out.println("Enter 6 to see the number of slots with respective colour");
            System.out.println("Enter 7 to Exit");
            try {
                token = scan.nextInt();
            } catch (InputMismatchException imp) {
                System.out.println("Please Enter a valid number");
                break;
            } catch (Exception ee) {
                System.out.println(ee.getMessage());
            }
            switch (token) {
                case 1:
                    try {
                        managementService.registration();
                    } catch (ParkingSlotFullException p) {
                        System.out.println(p.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter the car Number:");
                    String carNum = scan.next().toLowerCase();
                    try {
                        managementService.pickUp(carNum);
                    } catch (CarNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (ParkingLotEmptyException pe) {
                        System.out.println(pe.getMessage());
                    }
                    break;
                case 3:
                    managementService.parkingStatus();
                    break;
                case 4:
                    System.out.println("Enter car number");
                    String carNumber = scan.next().toLowerCase();
                    try {
                        managementService.getSlotByCarNumber(carNumber);
                    } catch (CarNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter car colour");
                    String carColour = scan.next().toLowerCase();
                    try {
                        managementService.getCarsByColour(carColour);
                    } catch (CarNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Enter car colour");
                    carColour = scan.next().toLowerCase();
                    try {
                        managementService.getSlotsByCarColour(carColour);
                    } catch (CarNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception  ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid number");
            }

        }
    }

    public static void slotRegistration() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of parking lots");
        try {
            int slotList = scan.nextInt();
            ParkingManagementService.slotSize = slotList;
            System.out.println("Sucessfully created " + slotList + " parking lots");
//			scan.close();
        } catch (Exception ee) {
            System.out.println("Please enter a valid number");
            slotRegistration();
        }

    }
}
