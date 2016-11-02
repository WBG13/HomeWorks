public class ThreadBlocker extends Thread {
    private Coordinates coordinates;

    ThreadBlocker(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public void run() {
        synchronized (coordinates) {
            try {
                coordinates.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
