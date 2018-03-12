public class DisabledBay extends ParkingSpot{

    public static final char initialRepresentation = '@';

    public DisabledBay() {
        super.setInitialRepresentation(this.initialRepresentation);
        super.setRepresentation(this.initialRepresentation);
    }

    @Override
    public boolean canPark(Car car) {
        return (car instanceof DisabledCar) && super.isEmpty();
    }


    @Override
    public void unparkCar() {
        super.setRepresentation(initialRepresentation);
    }
}
