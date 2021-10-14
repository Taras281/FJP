public class Consumer implements Runnable {

    private final Parking parking;

    public Consumer(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            parking.in();
        }
    }
}
