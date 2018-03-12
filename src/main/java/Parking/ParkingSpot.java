package Parking;

import Cars.Car;
import Cars.DisabledCar;

public class ParkingSpot {

    private char representation = 'U';
    private char initialRepresentation = 'U';


    public boolean canPark(Car car) {
        return isEmpty() && !(car instanceof DisabledCar);
    }

    public boolean canPark() {
        return isEmpty();
    }

    public boolean isEmpty() {
        return representation == initialRepresentation;
    }

    public void setRepresentation(char representation) {
        this.representation = representation;
    }

    public void setInitialRepresentation(char initialRepresentation) {
        this.initialRepresentation = initialRepresentation;
    }

    public void parkCar(char carRepresentation) {
        this.representation = carRepresentation;
    }

    public void unparkCar() {
        this.setRepresentation(initialRepresentation);
    }

    @Override
    public String toString() {
        return String.valueOf(this.representation);
    }


}
