
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Sorter {
    int[] specimens = new int[10];

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public Sorter() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Sorter SorterProj = new Sorter();
        SorterProj.showActionListenerDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(700, 200);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350, 100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    void showActionListenerDemo() {
        headerLabel.setText("Choose your action");
        JPanel panel = new JPanel();

        JButton createButton = new JButton("Create random numbers");
        JButton iSortButton = new JButton("Insertion sorting");
        JButton bSortButton = new JButton("Bubble sorting");

        createButton.addActionListener(new CustomActionListener());
        panel.add(createButton);
        controlPanel.add(panel);
        mainFrame.setVisible(true);

        iSortButton.addActionListener(new iSortActionListener());
        panel.add(iSortButton);
        controlPanel.add(panel);
        mainFrame.setVisible(true);

        bSortButton.addActionListener(new bSortActionListener());
        panel.add(bSortButton);
        controlPanel.add(panel);
        mainFrame.setVisible(true);
    }

    class CustomActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < specimens.length; i++) {
                specimens[i] = (int) Math.round(Math.random() * 100);
            }
            statusLabel.setText(String.valueOf(specimens[0] + ", " + specimens[1] + ", " + specimens[2] + ", " +
                    specimens[3] + ", " + specimens[4] + ", " + specimens[5] + ", " + specimens[6] + ", " + specimens[7] + ", "
                    + specimens[8] + ", " + specimens[9]));
        }
    }

    class iSortActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            iSortData.doInsertionSort(specimens);
            statusLabel.setText(String.valueOf(specimens[0] + ", " + specimens[1] + ", " + specimens[2] + ", " +
                    specimens[3] + ", " + specimens[4] + ", " + specimens[5] + ", " + specimens[6] + ", " + specimens[7] + ", "
                    + specimens[8] + ", " + specimens[9]));
        }
    }

    class bSortActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Sort1.bubbleSort(specimens);
            statusLabel.setText(String.valueOf(specimens[0] + ", " + specimens[1] + ", " + specimens[2] + ", " +
                    specimens[3] + ", " + specimens[4] + ", " + specimens[5] + ", " + specimens[6] + ", " +
                    specimens[7] + ", " + specimens[8] + ", " + specimens[9]));
        }
    }

    public static class iSortData {

        public static int[] doInsertionSort(int[] specimens) {
            int temp;
            for (int i = 1; i < specimens.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (specimens[j] < specimens[j - 1]) {
                        temp = specimens[j];
                        specimens[j] = specimens[j - 1];
                        specimens[j - 1] = temp;
                    }
                }
            }
            return specimens;
        }
    }

    public static class Sort1 {
        private static void swap(int[] specimens, int index) {
            int temp = specimens[index - 1];
            specimens[index - 1] = specimens[index];
            specimens[index] = temp;
        }

        public static int[] bubbleSort(int[] specimens) {
            int[] resSpecimens = specimens;
            for (int i = 1; i < resSpecimens.length; i++) {
                for (int j = resSpecimens.length - 1; j >= i; j--) {
                    if (resSpecimens[j - 1] > resSpecimens[j]) swap(resSpecimens, j);
                }
            }
            return specimens;
        }
    }
}
