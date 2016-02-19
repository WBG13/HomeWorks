import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SorterView {
    private int mainFrameHeight, mainFrameWidth;
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    JButton[] buttons;

    private enum CMD {
        CREATE("Create random numbers"), ISORT("Insertion sorting"), BSORT("Bubble sorting"), QSORT("Quick sorting");

        CMD(String command) {
            this.command = command;
        }

        private String command;

        @Override
        public String toString() {
            return command;
        }
    }

    public SorterView() {
        prepareGUI();
    }

    public void setMainFrameWidth(double width) {
        this.mainFrameWidth = (int) width / 2;
    }

    public void setMainFrameHeight(double height) {
        this.mainFrameHeight = (int) height / 4;
    }

    private void prepareGUI() {

        mainFrame = new JFrame("Sorter");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setMainFrameWidth(screenSize.getWidth());
        setMainFrameHeight(screenSize.getHeight());
        mainFrame.setSize((mainFrameWidth), (mainFrameHeight));
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.add(headerLabel);
        mainFrame.add(statusLabel);

        createControlPane();
        mainFrame.setVisible(true);
    }

    public JPanel createControlPane() {
        CMD[] cmd = CMD.values();
        buttons = new JButton[cmd.length];
        JPanel pane, panel = new JPanel();
        pane = new JPanel();
        pane.setLayout(new FlowLayout());
        pane.add(panel);
        mainFrame.add(pane);
        for (int i = 0; i < cmd.length; i++) {
            buttons[i] = new JButton(String.valueOf(cmd[i]));
            panel.add(buttons[i]);
        }
        return panel;
    }

    public void sText(int[] specimens) {
        StringBuilder sb = new StringBuilder();
        for (int i : specimens) sb.append(i != specimens[specimens.length - 1] ? i + ", " : i + ".");
        statusLabel.setText(sb.toString());
    }

    public void addSorterListener(ActionListener listenForSorterButton) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(listenForSorterButton);
        }
    }
}
