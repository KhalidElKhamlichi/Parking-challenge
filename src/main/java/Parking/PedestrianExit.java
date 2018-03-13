package Parking;

import Cars.Car;

public class PedestrianExit extends ParkingSpot {

    private final char representation = '=';

    public PedestrianExit() {
        super.initialRepresentation = this.representation;
        super.representation = this.representation;
    }

    @Override
    public boolean canPark(Car car) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean canPark() {
        return false;
    }
}
