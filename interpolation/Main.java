package LagrangesInterpolation.HW.GUI;

import LagrangesInterpolation.HW.GUI.Controller.Controller;

/**
 * Created by TH-221 on 18.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        int [] x = {0, 10, 20, 30, 40};
        int [] y =  {4, 16, 20, 50, 55};
        Controller cn = new Controller(x ,y);
    }
}
