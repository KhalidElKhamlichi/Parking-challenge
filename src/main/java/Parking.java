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
            if(parkingSpot.isEmpty()) {
                availableBays++;
            }
        }
        return availableBays;
    }

    public void addPedestrianExit(int i) {
//        System.out.println("available bays: "+getAvailableBays());
        parkingSpots.set(i, new PedestrianExit());
    }

    public void addDisabledBay(int i) {
//        System.out.println("list size: "+parkingSpots.size()+" - actual size: "+this.size);
        parkingSpots.set(i, new DisabledBay());
    }

    public int parkCar(char c) {
        Car car = CarFactory.getCar(c);
        List<ParkingSpot> closestSpots = getClosestSpotsToPedestrianExit(car);
        ParkingSpot parkingSpot = getClosestSpotToEntrance(closestSpots);
        parkingSpot.parkCar(car.getName());
//        System.out.println(toString());
        return parkingSpots.indexOf(parkingSpot);
    }

    private ParkingSpot getClosestSpotToEntrance(List<ParkingSpot> closestSpots) {

        ParkingSpot parkingSpot = closestSpots.get(0);

        int min = Integer.MAX_VALUE;
        for(ParkingSpot spot : closestSpots) {

            if(parkingSpots.indexOf(spot) < min) {
                parkingSpot = spot;
                min = parkingSpots.indexOf(spot);
            }

        }

        return parkingSpot;
    }

    private List<ParkingSpot> getClosestSpotsToPedestrianExit(Car car) {
        List<ParkingSpot> pedestrianExits = getPedestrianExits();

        List<ParkingSpot> closestParkingSpots = new ArrayList<>();
        while (closestParkingSpots.isEmpty()) {
            for (ParkingSpot pedestrianExit : pedestrianExits) {

                int indexOfPedestrianExit = parkingSpots.indexOf(pedestrianExit);
                int it1 = indexOfPedestrianExit - 1;
                int it2 = indexOfPedestrianExit + 1;

                if (parkingSpots.get(it1).canPark(car))
                    closestParkingSpots.add(parkingSpots.get(it1));

                if (parkingSpots.get(it2).canPark(car))
                    closestParkingSpots.add(parkingSpots.get(it2));

                it1--;
                it2++;
            }
        }
        return closestParkingSpots;

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

    public boolean unparkCar(int firstCarBayIndex) {
        return false;
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
