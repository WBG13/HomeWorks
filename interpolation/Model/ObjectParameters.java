public class ObjectParameters {
    private final Point[] points;
    private double interval;

    public Point[] getPoints() {
        return points;
    }

    public double getInterval() {
        return interval;
    }

    public void setInterval(double interval) {
        this.interval = interval;
    }

    ObjectParameters(ObjectParametersBuilder opb) {
        this.points = opb.getPoints();
        this.interval = opb.getInterval();
    }
}
