package tests;


import com.parkingmanagement.exception.CarNotFoundException;
import com.parkingmanagement.exception.ParkingLotEmptyException;
import com.parkingmanagement.exception.ParkingSlotFullException;
import com.parkingmanagement.model.ParkedCars;
import com.parkingmanagement.service.ParkingManagementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingManagementServiceTest {

    private ParkingManagementService parkingManagementService = new ParkingManagementService();

    // test for adding the register car and checking the input list
    @Test
    public void registrationTests(){
        ParkingManagementService.parkingList.clear();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2915","white",3));
        assertEquals(3,ParkingManagementService.parkingList.size());
    }

    // test for the checking the exception thrown at time of slot if full
    @Test
    public void registrationTestsException() throws ParkingSlotFullException{
        ParkingManagementService.slotSize = 2;
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2813","white",1));
        Assertions.assertThrows( ParkingSlotFullException.class,() ->{
            parkingManagementService.registration();
        });
    }

    // test for getting the first nearest slot when there is no parked vechile
    @Test
    public void testSlotsNumber(){
        ParkingManagementService.slotSize = 2;
        Assertions.assertNotNull(parkingManagementService.getSlot());
    }

    //test for the getting 0 as no slot available
    @Test
    public void testNoSlotNumber(){
        ParkingManagementService.slotSize = 1;
        ParkingManagementService parkingManage = new ParkingManagementService();
        parkingManage.parkingStatus[0]=true;
        assertEquals(0,parkingManage.getSlot());
    }

    //test for the updation of status of the parking slots as booked
    @Test
    public void testUpdationSlotsPick(){
        ParkingManagementService.slotSize = 1;
        ParkingManagementService parkingManage = new ParkingManagementService();
        parkingManage.updateStatus(1,true);
        assertEquals(true,parkingManage.parkingStatus[0]);
    }

    //test for the updation of status of the parking slots as free
    @Test
    public void testUpdationSlotsFree(){
        ParkingManagementService.slotSize = 1;
        ParkingManagementService parkingManage = new ParkingManagementService();
        parkingManage.updateStatus(1,false);
        assertEquals(false,parkingManage.parkingStatus[0]);
    }

    //for pickup the car from lobby test
    @Test
    public void testPickingCar() throws CarNotFoundException, ParkingLotEmptyException {
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.clear();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        park.pickUp("KA-07-2913");
        assertEquals(1,ParkingManagementService.parkingList.size());
    }

    //for pickup the car from lobby test that throws exception for carnot found
    @Test
    public void testPickingCarNotFound() throws CarNotFoundException, ParkingLotEmptyException {
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertThrows( CarNotFoundException.class,() ->{
            park.pickUp("KA-07-2919");
        });
    }

    //for pickup the car from lobby test that throws exception for lobby is empty
    @Test
    public void testPickingCarFromEmptyLobby() throws CarNotFoundException, ParkingLotEmptyException {
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.clear();
        Assertions.assertThrows( ParkingLotEmptyException.class,() ->{
            park.pickUp("KA-07-2919");
        });
    }

    // test for the getCarsByColour for wrong colours
    @Test
    public void testgetCarsByColourException(){
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertThrows(CarNotFoundException.class,()->{
            park.getCarsByColour("red");
        });
    }

    // test for the getCarsByColour for wrong colours
    @Test
    public void testgetCarsByColour(){
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertDoesNotThrow(()->{
            park.getCarsByColour("blue");
        });
    }

    // test for slot number whn car found
    @Test
    public void testgetSlotByCarNumber() throws Exception {
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.clear();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertDoesNotThrow(()->{
            park.getSlotByCarNumber("KA-07-2914");
        });
    }

    @Test
    public void testgetSlotByCarNumberException() throws Exception {
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.clear();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertThrows(NullPointerException.class,()->{
            park.getSlotByCarNumber("KA-07-2918");
        });
    }

    // test for the getSlotsByColour for wrong colours
    @Test
    public void getSlotsByColourException(){
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertThrows(CarNotFoundException.class,()->{
            park.getSlotsByCarColour("red");
        });
    }

    // test for the getCarsByColour for wrong colours
    @Test
    public void getSlotsByColour(){
        ParkingManagementService.slotSize = 2;
        ParkingManagementService park = new ParkingManagementService();
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2913","blue",1));
        ParkingManagementService.parkingList.add(new ParkedCars("KA-07-2914","green",2));
        Assertions.assertDoesNotThrow(()->{
            park.getCarsByColour("blue");
        });
    }

}
