import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SorterView {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    JButton createButton = new JButton("Create random numbers");
    JButton iSortButton = new JButton("Insertion sorting");
    JButton bSortButton = new JButton("Bubble sorting");
    JButton qSortButton = new JButton("Quick sorting");

    public SorterView() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Sorter");
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
        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);

        controlPanel = createControlPane();
        mainFrame.setVisible(true);
    }
    public JPanel createControlPane(){
        JPanel pane,panel = new JPanel();
        pane = new JPanel();
        pane.setLayout(new FlowLayout());
        pane.add(panel);
        mainFrame.add(pane);

        panel.add(createButton);
        panel.add(iSortButton);
        panel.add(bSortButton);
        panel.add(qSortButton);
        return panel;
    }

    public void sText(int[] specimens) {
        StringBuilder sb = new StringBuilder();
        for (int i : specimens) sb.append(i != specimens[specimens.length - 1] ? i + ", " : i + ".");
        statusLabel.setText(sb.toString());
    }

    public void addSorterListener(ActionListener listenForSorterButton) {
        createButton.addActionListener(listenForSorterButton);
        iSortButton.addActionListener(listenForSorterButton);
        bSortButton.addActionListener(listenForSorterButton);
        qSortButton.addActionListener(listenForSorterButton);
    }
}
