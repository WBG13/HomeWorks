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
    public String localData;

    public JTextArea outputData;
    java.util.List<String> message = new ArrayList<>();

    public Con2MySql() {

        JFrame frame = new JFrame("");
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

        JButton get1Button = new JButton("Show");
        get1Button.addActionListener(this);
        buttonPanel.add(get1Button);

        JButton get2Button = new JButton("Find");
        get2Button.addActionListener(this);
        buttonPanel.add(get2Button);

        JButton get3Button = new JButton("Put");
        get3Button.addActionListener(this);
        buttonPanel.add(get3Button);

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
        contentPane.add(textPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Show":
                nameData = theTextName.getText();
                new show();
                break;
            case "Find":
                nameData = theTextName.getText();
                telData = theTextTel.getText();
                find(nameData, telData);

                break;
            case "Put":
                nameData = theTextName.getText();
                telData = theTextTel.getText();
                insert(nameData, telData);
                break;
            case "Clear":
                theTextName.setText("");
                theTextTel.setText("");
                outputData.setText("");
                break;
        }
    }

    public static void main(String[] args) {
        Con2MySql guiMemo = new Con2MySql();
        guiMemo.setVisible(true);

    }

    public void sText(java.util.List name) {
        String p = String.valueOf(name);
        p = p.replace("[", "");
        p = p.replace("]", "");
        outputData.setText(p);
        message = new ArrayList<>();
    }

    public void con(String findRequest) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/junk", uName, uPass);
            com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) con.createStatement();
            ResultSet rs = stmt.executeQuery(findRequest);
            while (rs.next()) {
                message.add("\nName: " + rs.getString("user_name") + ", Telephone: " + rs.getString("user_tel"));
            }
            sText(message);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void insert(String name, String tel) {
        if (!Objects.equals(name, "") && !Objects.equals(tel, "")) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/junk", uName, uPass);
                com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) con.createStatement();
                stmt.executeUpdate("INSERT INTO NoteBook " + "VALUES ('" + name + "', '" + tel + "');");
                message.add("Data has been added successfully!");
                sText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            message.add("Please enter user name and telephone!");
        sText(message);
    }

    public void find(String name, String tel) {
        {
            if (Objects.equals(name, "") && Objects.equals(tel, "")) {
                message.add("Please enter user name or telephone!");
                sText(message);
            } else if (!Objects.equals(name, "")) {
                localData = "select * from NoteBook where user_name='" + name + "'";
                con(localData);
            } else {
                localData = "select * from NoteBook where user_tel='" + tel + "'";
                con(localData);
            }
        }
    }

    public class show {
        {
            localData = "SELECT * FROM NoteBook";
            con(localData);
        }
    }
}
