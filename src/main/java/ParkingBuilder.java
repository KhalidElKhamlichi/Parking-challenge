public class ParkingBuilder {

    private Parking parking;

    public ParkingBuilder withSquareSize(int size) {
        parking = new Parking(size);
        return this;
    }

    public Parking build() {
        return parking;
    }

    public ParkingBuilder withPedestrianExit(int i) {
        parking.addPedestrianExit(i);
        return this;
    }

    public ParkingBuilder withDisabledBay(int i) {
        parking.addDisabledBay(i);
        return this;
    }
}
