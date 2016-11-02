public class ThreadController {
    private ObjectParameters objectParameters;
    private int maxNumberOfThreads;
    private Counter counter;

    public ThreadController(ObjectParameters objectParameters, int maxNumberOfThreads) {
        this.objectParameters = objectParameters;
        this.maxNumberOfThreads = maxNumberOfThreads;
        counter = new Counter(maxNumberOfThreads);
    }

    public void launchThreads(Coordinates coordinates) {
        ThreadBlocker tb = new ThreadBlocker(coordinates);
        tb.start();
        ThreadLauncher tl = new ThreadLauncher(coordinates, objectParameters);
        tl.setInterval(counter, maxNumberOfThreads);

        for (int i = 0; i < maxNumberOfThreads; i++) {
            new Thread(tl, String.valueOf(i)).start();
        }
    }
}
