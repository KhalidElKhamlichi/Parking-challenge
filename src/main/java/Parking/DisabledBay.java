package Parking;

import Cars.Car;
import Cars.DisabledCar;

public class DisabledBay extends ParkingSpot{

    public static final char initialRepresentation = '@';

    public DisabledBay() {
        super.initialRepresentation = this.initialRepresentation;
        super.representation = this.initialRepresentation;
    }

    @Override
    public boolean canPark(Car car) {
        return (car instanceof DisabledCar) && super.isEmpty();
    }


    @Override
    public void unparkCar() {
        super.representation = initialRepresentation;
    }
}
