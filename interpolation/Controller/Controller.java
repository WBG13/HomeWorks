package LagrangesInterpolation.HW.GUI.Controller;

import LagrangesInterpolation.HW.GUI.GUI.GUI;
import LagrangesInterpolation.HW.GUI.Model.Interpolator;

import javax.swing.*;

/**
 * Created by TH-221 on 18.09.2016.
 */
public class Controller {

    GUI graphic;
    private int [] distanceOnX;
    private final int [] x , y;

    public Controller(int[] x, int[] y){
        this.x = x;
        this.y = y;
        Interpolator interpolator = new Interpolator(x, y);


        graphic = new GUI(x , y);
        run();
    }
        public void run() {
            graphic.createAndShowGui(graphic);
        }

    public void setDistanceOnX(int[] distanceOnX) {
        this.distanceOnX = distanceOnX;
    }
}
