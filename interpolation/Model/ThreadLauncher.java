public class ThreadLauncher extends Thread {
    private Coordinates coordinates;
    private ObjectParameters objectParameters;
    private Counter counter;
    private int maxNumberOfThreads;

    ThreadLauncher(final Coordinates coordinates, final ObjectParameters objectParameters) {
        this.objectParameters = objectParameters;
        this.coordinates = coordinates;
    }

    public void setInterval(Counter counter, final int maxNumberOfThreads) {
        this.counter = counter;
        this.maxNumberOfThreads = maxNumberOfThreads;
    }

    @Override
    public void run() {
        Interpolator ip = new Interpolator(objectParameters, maxNumberOfThreads);

        int[] output = ip.getInterpolatedArray(objectParameters.getInterval() * Integer.parseInt(Thread.currentThread().getName()), objectParameters.getInterval() * maxNumberOfThreads);
        for (int i = 0; i < output.length; i++) {
            coordinates.addCoordinates(output[i], i * maxNumberOfThreads + Integer.parseInt(Thread.currentThread().getName()));
        }

        counter.decreaseNumber();
        if (counter.getNumber() == 0) {
            synchronized (coordinates) {
                try {
                    coordinates.notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
