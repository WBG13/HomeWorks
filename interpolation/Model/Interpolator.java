package LagrangesInterpolation.HW.GUI.Model;


/**
 * Created by TH-221 on 13.09.2016.
 */
public class Interpolator {
    private int[] x;
    private int[] y;

    public Interpolator(int[] x, int[] y) {
        this.x = x;
        this.y = y;
    }

    public int interpolate(double distanceOnX) {
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
        return (int) result;
    }

    public int[] getInterpolatedArray(double distanceOnXAxis) {
        int[] output;
        int[] axesXInterval;
        double distance = ((double) x[x.length - 1]);

        int toMany = (int) distance;
        output = new int[((int) (toMany / (distance * distanceOnXAxis))) + 1];
        axesXInterval = new int[(int) (toMany / distanceOnXAxis)];

        double currentPositionOnX = 0;

        System.out.println(distance);
        System.out.println(distanceOnXAxis);
        for (int i = 0; currentPositionOnX < (int) distance; i++) {
            axesXInterval[i] = (int) currentPositionOnX;
            output[i] = interpolate(currentPositionOnX);
            currentPositionOnX += (distance * distanceOnXAxis);
            System.out.println("Current position = " + currentPositionOnX);
        }
        return output;
    }
}

