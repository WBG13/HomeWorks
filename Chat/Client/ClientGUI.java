import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ClientGUI extends Application {

    static javafx.scene.control.TextArea sceneTitle;
    public int PORT;
    public String HOST;
    public String uName = null;
    public static Scene firstScene;
    public Scene secondScene;

    Stage mainStage;
    Scene chatScene;

    public static void setResults(ArrayList res) {
        String p = String.valueOf(res);
        p = p.replace("[", "");
        p = p.replace("]", "");
        p = p.replace(",", "");
        sceneTitle.setText(p);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        firstStage();

    }

    public void firstStage() throws Exception {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        firstScene = new Scene(grid, 400, 600);

        mainStage.setScene(firstScene);
        chatScene = firstScene;
        mainStage.setResizable(false);

        Text sceneTitle = new Text();
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 1, 1);


        Label userName = new Label("Name goes here:");
        grid.add(userName, 0, 1);

        Label port = new Label("Port:");
        grid.add(port, 0, 2);

        Label host = new Label("Host:");
        grid.add(host, 0, 3);

        TextField userNameField = new TextField();
        grid.add(userNameField, 1, 1);

        TextField portField = new TextField("19000");
        grid.add(portField, 1, 2);

        TextField hostField = new TextField("localhost");
        grid.add(hostField, 1, 3);


        Button btn = new Button("Connect");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);

        userNameField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if (userNameField.getText().equals("")) {
                    userName.setTextFill(Color.rgb(255, 0, 0));
                } else {
                    userName.setTextFill(Color.rgb(0, 0, 0));
                    uName = userNameField.getText();
                    PORT = Integer.parseInt((portField.getText()));
                    HOST = hostField.getText();
                    try {
                        secondStage();
                    } catch (Exception e) {
                        port.setTextFill(Color.rgb(255, 0, 0));
                        host.setTextFill(Color.rgb(255, 0, 0));
                        e.printStackTrace();
                    }
                }
            }
        });
        btn.setOnAction(event -> {
            if (userNameField.getText().equals("")) {
                userName.setTextFill(Color.rgb(255, 0, 0));
            } else {
                userName.setTextFill(Color.rgb(0, 0, 0));
                uName = userNameField.getText();
                PORT = Integer.parseInt((portField.getText()));
                HOST = hostField.getText();
                try {
                    secondStage();
                } catch (Exception e) {
                    port.setTextFill(Color.rgb(255, 0, 0));
                    host.setTextFill(Color.rgb(255, 0, 0));
                    e.printStackTrace();
                }
            }
        });
        grid.add(hbBtn, 1, 4);
        mainStage.show();
    }


    public void secondStage() throws Exception {
        Connection con = new Connection(HOST, PORT);

        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(30);
        grid2.setPadding(new Insets(70, 70, 70, 80));

        Text sceneTitle2 = new Text();
        sceneTitle2.setFont(new Font(20));
        sceneTitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid2.add(sceneTitle2, 0, 0, 1, 1);


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        secondScene = new Scene(grid, 400, 600);
        secondScene.getOnSwipeDown();
        mainStage.setScene(secondScene);
        chatScene = secondScene;

        TextField userTextField = new TextField();
        userTextField.setPrefSize(350, 20);
        grid.add(userTextField, 0, 47, 1, 1);


        sceneTitle = new TextArea("  ");
        sceneTitle.setFont(Font.font("Verdana", 12));
        sceneTitle.setEditable(false);
        sceneTitle.setPrefSize(400, 600);
        grid.add(sceneTitle, 0, 0, 14, 46);

        Button btnSnd = new Button("Send"); //TODO Add visual button "Send"
        HBox hbBtn = new HBox();

        Button btnEx = new Button("Exit");

        grid.add(btnSnd, 1, 47, 6, 1);
        grid.add(btnEx, 6, 47, 7, 1);

        mainStage.setOnCloseRequest(t -> System.exit(0));
        userTextField.setOnKeyPressed(ke -> {

            if (ke.getCode().equals(KeyCode.ENTER)) {
                con.sendMsg(("<" + uName + ">: " + userTextField.getText()), con.out);
                userTextField.clear();
            }
        });

        btnSnd.setOnAction(event -> {
            con.sendMsg(("<" + uName + ">: " + userTextField.getText()), con.out);
            userTextField.clear();
        });
        btnEx.setOnAction(event ->
                System.exit(0));
        grid.add(hbBtn, 1, 47);
        mainStage.show();
    }
}
