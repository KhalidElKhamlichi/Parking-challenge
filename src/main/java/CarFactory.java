public class CarFactory {
    public static Car getCar(char c) {
        if(c == 'D')
            return new DisabledCar();

        return new Car(c);
    }
}
