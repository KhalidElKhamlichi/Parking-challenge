package Parking;

import Cars.Car;
import Cars.DisabledCar;

public class ParkingSpot {

    protected char representation = 'U';
    protected char initialRepresentation = 'U';


    public boolean canPark(Car car) {
        return isEmpty() && !(car instanceof DisabledCar);
    }

    public boolean canPark() {
        return isEmpty();
    }

    public boolean isEmpty() {
        return representation == initialRepresentation;
    }


    public void parkCar(char carRepresentation) {
        this.representation = carRepresentation;
    }

    public void unparkCar() {
        this.representation = initialRepresentation;
    }

    @Override
    public String toString() {
        return String.valueOf(this.representation);
    }


}
