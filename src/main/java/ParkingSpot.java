public class ParkingSpot {

    private char representation = 'U';
    private char initialRepresentation = 'U';


    public boolean canPark(Car car) {
        return isEmpty() && !(car instanceof DisabledCar);
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

    @Override
    public String toString() {
        return String.valueOf(this.representation);
    }
}
