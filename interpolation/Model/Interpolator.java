public class Interpolator {
    private int[] x;
    private int[] y;
    private int maxNumberOfThreads = 0;

    public Interpolator(ObjectParameters objectParameters, int maxNumberOfThreads) {
        int[] x = new int[objectParameters.getPoints().length];
        int[] y = new int[objectParameters.getPoints().length];
        this.maxNumberOfThreads = maxNumberOfThreads;
        for (int i = 0; i < objectParameters.getPoints().length; i++) {
            x[i] = objectParameters.getPoints()[i].x;
            y[i] = objectParameters.getPoints()[i].y;
        }
        this.x = x;
        this.y = y;
    }

    private int interpolate(double distanceOnX) {
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            double k = 1;
            for (int j = 0; j < y.length; j++) {
                if (j != i) {
                    k *= (distanceOnX - x[j]) / (x[i] - x[j]);
                }
            }
            result += k * y[i];
        }
        Checker checker = new Checker();
        return ((int) result + checker.rounding(result));
    }


    public int[] getInterpolatedArray(double firstPosition, double interval) {
        double maxValueOfXAxisInDouble = ((double) x[x.length - 1]);
        double intervalOnXAxis = maxValueOfXAxisInDouble * interval;
        double currentPositionOnX = maxValueOfXAxisInDouble * firstPosition;
        int maxStepsInInt = (int) ((maxValueOfXAxisInDouble / (maxValueOfXAxisInDouble * interval)));
        double minIntervalThreadStep = ((maxValueOfXAxisInDouble * (interval / maxNumberOfThreads)));
        int numberExistingOnNextLine = (maxStepsInInt * intervalOnXAxis) + currentPositionOnX - minIntervalThreadStep < maxValueOfXAxisInDouble ? 1 : 0;
        int[] output = new int[maxStepsInInt + numberExistingOnNextLine];

        for (int i = 0; (currentPositionOnX - minIntervalThreadStep) < maxValueOfXAxisInDouble; i++) {
            output[i] = interpolate(currentPositionOnX);
            currentPositionOnX += intervalOnXAxis;

        }
        return output;
    }
}
