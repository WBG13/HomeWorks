import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Con2MySql extends JFrame implements ActionListener {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;

    private JTextField theTextName;
    private JTextField theTextTel;
    private String uName;
    private String uPass;
    public String nameData;
    public String telData;

    public JTextArea outputData;
    java.util.List<String> message = new ArrayList<>();

    public Con2MySql() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Login", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username = new JTextField();
        controls.add(username);
        JPasswordField password = new JPasswordField();
        controls.add(password);
        panel.add(controls, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(frame, panel, "LOGIN", JOptionPane.QUESTION_MESSAGE);

        uName = username.getText();
        uPass = password.getText();

        try {
            DriverManager.getConnection("jdbc:mysql://localhost/junk", uName, uPass);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        setSize(WIDTH, HEIGHT);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton showButton = new JButton("Show");
        showButton.addActionListener(this);
        buttonPanel.add(showButton);

        JButton findButton = new JButton("Find");
        findButton.addActionListener(this);
        buttonPanel.add(findButton);

        JButton putButton = new JButton("Put");
        putButton.addActionListener(this);
        buttonPanel.add(putButton);

        JButton deleteButton = new JButton("Erase");
        deleteButton.addActionListener(this);
        buttonPanel.add(deleteButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        JPanel textPanel = new JPanel();
        JLabel nameLabel = new JLabel("Name: ");

        theTextName = new JTextField(8);
        theTextName.setBackground(Color.white);

        JLabel telLabel = new JLabel("Telephone: ");

        theTextTel = new JTextField(8);
        theTextTel.setBackground(Color.white);

        outputData = new JTextArea(15, 30);
        JScrollPane scrollableTextArea = new JScrollPane(outputData);
        JScrollPane scrollPane = new JScrollPane(textPanel);

        textPanel.add(nameLabel, BorderLayout.SOUTH);
        textPanel.add(theTextName, BorderLayout.SOUTH);
        textPanel.add(telLabel, BorderLayout.SOUTH);
        textPanel.add(theTextTel, BorderLayout.SOUTH);
        contentPane.add(scrollPane);
        textPanel.add(scrollableTextArea);

        contentPane.add(textPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        nameData = theTextName.getText();
        telData = theTextTel.getText();

        switch (actionCommand) {
            case "Show":
                recToDB("SELECT * FROM NoteBook", 0);
                break;
            case "Find": {
                if (Objects.equals(nameData, "") && Objects.equals(telData, "")) {
                    message.add("Please enter user name or telephone!");
                    sText(message);
                } else if (!Objects.equals(nameData, "")) {
                    recToDB("select * from NoteBook where user_name='" + nameData + "'", 0);
                } else {
                    recToDB("select * from NoteBook where user_tel='" + telData + "'", 0);
                }
            }
            break;
            case "Put": {
                if (!Objects.equals(nameData, "") && !Objects.equals(telData, "")) {
                    recToDB("INSERT INTO NoteBook " + "VALUES ('" + nameData + "', '" + telData + "');", 1);
                    message.add(nameData + " with phone number: " + telData + " has been added!");
                } else
                    message.add("Please enter user name and telephone!");
                sText(message);
            }
            break;
            case "Erase": {
                if (!Objects.equals(nameData, "") && !Objects.equals(telData, "")) {
                    recToDB("delete from NoteBook where User_Name ='" + nameData + "' and User_Tel ='" + telData + "' limit 1;", 1);
                    message.add(nameData + " with phone number: " + telData + " has been erased!");
                } else
                    message.add("Please enter user name and telephone!");
                sText(message);
            }
            break;
            case "Clear":
                theTextName.setText("");
                theTextTel.setText("");
                outputData.setText("");
                break;
        }
    }

    public static void main(String[] Lolly) {
        Con2MySql guiMemo = new Con2MySql();
        guiMemo.setVisible(true);
    }

    public void sText(java.util.List input) {
        String p = String.valueOf(input);
        p = p.replace("[", "");
        p = p.replace("]", "");
        outputData.setText(p);
        message = new ArrayList<>();
    }

    public void recToDB(String inputRequest, int comm) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/junk", uName, uPass);
            com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) con.createStatement();
            if (comm == 0) {
                ResultSet rs = stmt.executeQuery(inputRequest);
                while (rs.next()) {
                    message.add("\nName: " + rs.getString("user_name") + ", Telephone: " + rs.getString("user_tel"));
                }
                sText(message);
            } else stmt.executeUpdate(inputRequest);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
