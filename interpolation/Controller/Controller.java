import LagrangesInterpolation.HW.GUI.GUI.GUI;
import LagrangesInterpolation.HW.GUI.Model.Coordinates;
import LagrangesInterpolation.HW.GUI.Model.ObjectParameters;
import LagrangesInterpolation.HW.GUI.Model.ObjectParametersBuilder;
import LagrangesInterpolation.HW.GUI.Model.ThreadController;

import java.awt.*;

/**
 * Created by TH-221 on 18.09.2016.
 */
public class Controller {
    private static final double distanceOnXAxis = 0.02;
    GUI graphic;
    int interval;
    int[] output;


    public Controller(int[] x, int[] y) {
        ObjectParameters objectParameters = createObject(x, y);
        objectParameters.setInterval(distanceOnXAxis);
        Point[] points = new Point[x.length];
        double xP = x[x.length - 1] * distanceOnXAxis;
        int size = (int) (x[x.length - 1] / xP);
        Coordinates coordinates = new Coordinates(size + 1);
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point();
            points[i].x = x[i];
            points[i].y = y[i];
        }

        ThreadController tc = new ThreadController(objectParameters, 4);
        tc.launchThreads(coordinates);
        output = coordinates.getCoordinates();
        graphic = new GUI(objectParameters, coordinates);

        activeGUI();


    }

    private ObjectParameters createObject(int[] x, int[] y) {
        Point[] points = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            points[i] = new Point();
            points[i].x = x[i];
            points[i].y = y[i];
        }
        return new ObjectParametersBuilder().points(points).interval(interval).build();
    }

    private void activeGUI() {
        graphic.createAndShowGui(graphic);
    }

}
