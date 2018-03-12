package Parking;

import Cars.Car;
import Cars.CarFactory;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private int size;
    private List<ParkingSpot> parkingSpots;

    public Parking(int size) {
        this.size = size;
        this.parkingSpots = new ArrayList<>();
        init();
    }

    private void init() {
        for(int i=0; i<size*size; i++) {
            parkingSpots.add(new ParkingSpot());
        }
    }

    public int getAvailableBays() {
        int availableBays = 0;
        for(ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot.canPark()) {
                availableBays++;
            }
        }
        return availableBays;
    }

    public void addPedestrianExit(int i) {
        parkingSpots.set(i, new PedestrianExit());
    }

    public void addDisabledBay(int i) {
        parkingSpots.set(i, new DisabledBay());
    }

    public int parkCar(char c) {

        Car car = CarFactory.getCar(c);

        ParkingSpot closestSpot = getClosestSpotToPedestrianExit(car);

        if (closestSpot == null)
            return -1;


        closestSpot.parkCar(car.getName());

        return parkingSpots.indexOf(closestSpot);
    }

    private ParkingSpot getClosestSpotToPedestrianExit(Car car) {
        List<ParkingSpot> pedestrianExits = getPedestrianExits();

        ParkingSpot closestParkingSpot = null;

        int searchRange = 1;

        while (closestParkingSpot == null && searchRange < parkingSpots.size()) {

            for (ParkingSpot pedestrianExit : pedestrianExits) {

                int indexOfPedestrianExit = parkingSpots.indexOf(pedestrianExit);
                int leftIterator = indexOfPedestrianExit - searchRange;
                int rightIterator = indexOfPedestrianExit + searchRange;

                if(leftIterator >= 0 && rightIterator < parkingSpots.size()) {

                    if (parkingSpots.get(leftIterator).canPark(car)) {
                        closestParkingSpot = parkingSpots.get(leftIterator);
                        break;
                    }

                    if (parkingSpots.get(rightIterator).canPark(car)) {
                        closestParkingSpot = parkingSpots.get(rightIterator);
                        break;
                    }
                }

            }
            searchRange++;

        }
        return closestParkingSpot;

    }

    private List<ParkingSpot> getPedestrianExits() {
        List<ParkingSpot> pedestrianExits = new ArrayList<>();

        for(ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot instanceof PedestrianExit) {
                pedestrianExits.add(parkingSpot);
            }
        }
        return pedestrianExits;
    }

    public boolean unparkCar(int index) {
        ParkingSpot parkingSpot = parkingSpots.get(index);

        if (parkingSpot.isEmpty())
            return false;

        parkingSpot.unparkCar();
        return true;
    }

    @Override
    public String toString() {
        StringBuilder parkingRepresentation = new StringBuilder();

        for(int i=0; i<size; i++) {

            StringBuilder lane = new StringBuilder();

            for(int j=size*i; j<size*(i+1); j++) {
                lane.append(parkingSpots.get(j).toString());
            }
            if(i % 2 != 0)
                lane.reverse();
            parkingRepresentation.append(lane.toString());

            if(i < size-1)
                parkingRepresentation.append("\n");
        }

        return parkingRepresentation.toString();
    }
}
