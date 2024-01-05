package live.ashish.cpjava.systemdesign.parkinglot;


// this problem is to design a parking lot with given capacity
// it can also store vehicles of different types

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {
    List<ParkingSpot> parkingSpots = new ArrayList<>();

    public ParkingLot(int capacity) {
        for (int i = 0; i < capacity; i++) {
            parkingSpots.add(new ParkingSpot(VehicleType.EMPTY)); // added all empty slots
        }
    }

    public long capacity() {
        // return capacity of parking
        return parkingSpots.stream()
                .filter(parkingSpot -> parkingSpot.vehicleType == VehicleType.EMPTY)
                .count();
    }
    public void status() {
        for (int i = 0; i < parkingSpots.size(); i++) {
            System.out.print("("+parkingSpots.get(i).vehicleType+ ","+i+")  ");
        }
        System.out.println();
    }

    public boolean isAvailable() {
        return capacity() != 0;
    }

    public ParkingTicket park(VehicleType vehicleType) {
        if (isAvailable()) { // if parking is available
            for (int i = 0; i < parkingSpots.size(); i++) {
                if (parkingSpots.get(i).vehicleType == VehicleType.EMPTY) {
                    parkingSpots.set(i, new ParkingSpot(vehicleType));
                    return new ParkingTicket(i);  // return back the index
                }
            }
        }
        System.out.println("Sorry, Parking is full!");
        return new ParkingTicket(-1);
    }

    public ParkingTicket unPark(ParkingTicket parkingTicket) {
        if (parkingTicket.getTicketNumber() >= 0 && parkingTicket.getTicketNumber() < parkingSpots.size()) {
            parkingSpots.set(parkingTicket.ticketNumber, new ParkingSpot(VehicleType.EMPTY));
            System.out.println("Your vehicle has been unparked successfully");
            return parkingTicket;
        }
        System.out.println("Invalid parking ticket!!!");
        return new ParkingTicket(-1);
    }
}

enum VehicleType {
    TRUCK, CAR, BUS, EMPTY
}

@Data
@AllArgsConstructor
class ParkingSpot {
    VehicleType vehicleType;
}


@Data
@AllArgsConstructor
class ParkingTicket {
    int ticketNumber;
}
