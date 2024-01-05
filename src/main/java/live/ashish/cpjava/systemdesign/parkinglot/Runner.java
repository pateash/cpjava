package live.ashish.cpjava.systemdesign.parkinglot;


import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Please choose capacity of your parking lot: eg. 10");
        ParkingLot parkingLot = new ParkingLot(input.nextInt());
        while (true) {
            System.out.println("Please choose your command, ['PARK','CAPACITY', 'UNPARK', 'STATUS']");
            String type = input.next();
            switch (type.toUpperCase()) {
                case "PARK":
                    System.out.println("Please give vehicle type:");
                    String vehicleType = input.next().toUpperCase();
                    try {
                        ParkingTicket ticket = parkingLot.park(VehicleType.valueOf(vehicleType));
                        System.out.println("Your ticket is: " + ticket);
//                        System.out.println("Remaining capacity : " + parkingLot.capacity());
                        parkingLot.status();
                    } catch (IllegalArgumentException e) {
                        System.out.println("No vehicle Type matching: " + vehicleType);
                    }
                    break;
                case "UNPARK":
                    System.out.println("Please give parking ticket number:");
                    parkingLot.unPark(new ParkingTicket(input.nextInt()));
                    parkingLot.status();
                    break;
                case "CAPACITY":
                    System.out.println("Total vacant parkings: " + parkingLot.capacity());
                    break;
                case "STATUS":
                    parkingLot.status();
                    break;
                default:
                    System.out.println("Invalid command! \n Please try again.");
            }
            System.out.println();
        }
    }
}
