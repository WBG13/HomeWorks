public class ObjectParametersBuilder {
    private Point[] points;
    private int interval;

    public ObjectParametersBuilder interval(int interval) {
        this.interval = interval;
        return this;
    }

    public ObjectParametersBuilder points(Point[] points) {
        this.points = points;
        return this;
    }

    public Point[] getPoints() {
        return points;
    }

    public int getInterval() {
        return interval;
    }

    public ObjectParameters build() {
        return new ObjectParameters(this);
    }
}
