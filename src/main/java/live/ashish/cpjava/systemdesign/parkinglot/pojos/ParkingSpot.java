package live.ashish.cpjava.systemdesign.parkinglot.pojos;

import live.ashish.cpjava.systemdesign.parkinglot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpot {
   public VehicleType vehicleType;
}
