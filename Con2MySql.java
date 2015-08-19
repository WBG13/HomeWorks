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
    private String memo1;
    private String memo2;
    private String name;
    private String tel;

    private String uName;
    private String uPass;

    public JTextArea infoTel;


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


        infoTel = new JTextArea(15, 30);
        JScrollPane scrollableTextArea = new JScrollPane(infoTel);
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
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Show":
                memo1 = theTextName.getText();
                new show();
                break;
            case "Find":
                System.out.print("Start Find");
                memo1 = theTextName.getText();
                find(memo1);

                break;
            case "Put":
                System.out.println("Put active");
                memo1 = theTextName.getText();
                memo2 = theTextTel.getText();
                insert(memo1, memo2);
                break;
            case "Clear":
                theTextName.setText("");
                theTextTel.setText("");
                infoTel.setText("");
                break;
            default:
                theTextName.setText("Error");
                break;
        }
    }

    public static void main(String[] args) {
        Con2MySql guiMemo = new Con2MySql();
        guiMemo.setVisible(true);
    }

    public void sText(java.util.List name) {
        System.out.println("Seting Text");
        System.out.println("InfoTl: " + infoTel);
        String p = String.valueOf(name);
        p = p.replace("[", "");
        p = p.replace("]", "");
        infoTel.setText(p);
    }

    public void find(String input) {
        {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/junk",uName, uPass);
                com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) con.createStatement();

                String SQL = "select * from NoteBook where user_name='" + input + "'";
                ResultSet rs = stmt.executeQuery(SQL);

                java.util.List<String> mutafrukt = new ArrayList<>();

                while (rs.next()) {
                    mutafrukt.add("Name: " + rs.getString("user_name") + ", Telephone: " + rs.getString("user_tel"));
                    name = rs.getString("user_name");
                    tel = rs.getString("user_tel");
                }
                sText(mutafrukt);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void insert(String name, String tel) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/junk", uName, uPass);
            com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) con.createStatement();
            stmt.executeUpdate("INSERT INTO NoteBook " + "VALUES ('" + name + "', '" + tel + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class show {
        {
            ResultSet rs;
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/junk", "root", "Ta01Chi06");
                com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) con.createStatement();
                rs = stmt.executeQuery("SELECT * FROM NoteBook");
                java.util.List<String> mutafrukt = new ArrayList<>();

                while (rs.next()) {
                    mutafrukt.add("\nName: " + rs.getString("user_name") + ", Telephone: " + rs.getString("user_tel") + "");
                }

                sText(mutafrukt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
