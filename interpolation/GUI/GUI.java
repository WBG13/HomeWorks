package LagrangesInterpolation.HW.GUI.GUI;


import LagrangesInterpolation.HW.GUI.Model.Interpolator;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by TH-221 on 18.09.2016.
 */
public class GUI extends JPanel {
    private final int MAX_SCORE = 60;
    private final int PREF_W = 800;
    private final int PREF_H = 650;
    private final int BORDER_GAP = 30;
    private final Color GRAPH_COLOR = Color.green;
    private final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
    private final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private final int GRAPH_POINT_WIDTH = 12;
    private final int Y_HATCH_CNT = 10;
    private static int maxXParameter;

    private static final double intervalOnXAxis = 0.01;


    public Graphics2D g2;

    private static int[] xParameters;
    private static int[] yParameters;

    static double[] output;
    static int[] currentXPosition;

    public GUI(int[] scoresX, int[] scoresY) {
        this.xParameters = scoresX;
        this.yParameters = scoresY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (xParameters.length - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);


        java.util.List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < xParameters.length; i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - yParameters[i]) * yScale + BORDER_GAP);// + 30;
            xParameters[i] = x1;
            yParameters[i] = y1;
            System.out.println("x = " + xParameters[i] + ", y = " + yParameters[i]);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < xParameters.length - 1; i++) {
            int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (xParameters.length - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);

        int x1 = xParameters[0];
        int y1, y2, x2;

        int[] reformatYPosition = new int[output.length];
        for (int i = 0; i < reformatYPosition.length; i++) {
            reformatYPosition[i] = (int) ((MAX_SCORE - output[i]) * yScale + BORDER_GAP);
        }

        int maxXParameter = xParameters[xParameters.length - 1];
        int way = (int) (maxXParameter * intervalOnXAxis);
        x1 -= way;

        for (int z = 0; z < output.length - 1; z++) {

            x1 += way;
            y1 = (int) ((MAX_SCORE - output[z]) * yScale + BORDER_GAP);

            x2 = x1 + way;
            y2 = (int) ((MAX_SCORE - output[z + 1]) * yScale + BORDER_GAP);

            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);

        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public static void createAndShowGui(GUI mainPanel) {

        Interpolator ip = new Interpolator(xParameters, yParameters );

        double maxXAxisParameter = ((double) xParameters[xParameters.length - 1]);

        int maxXParameter = (int) maxXAxisParameter;
        output = new double[((int) (maxXParameter / (maxXAxisParameter * intervalOnXAxis))) + 1];
        currentXPosition = new int[(int) (maxXParameter / intervalOnXAxis)];

        double currentPositionOnX = 0;

        for (int i = 0; currentPositionOnX < maxXParameter; i++) {
            currentXPosition[i] = (int) currentPositionOnX;
            output[i] = ip.interpolate(currentPositionOnX);//, xParameters, yParameters);
            currentPositionOnX += (maxXAxisParameter * intervalOnXAxis);
        }

        JFrame frame = new JFrame("DrawGraph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

}
