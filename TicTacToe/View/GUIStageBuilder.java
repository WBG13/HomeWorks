package TicTacToe.View;

import TicTacToe.Controller.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIStageBuilder extends Application {
    static Stage mainStage;
    static GridPane pane = new GridPane();
    static Scene scene = new Scene(pane, 300, 300);


    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        mainStage.setScene(scene);
        mainStage.show();
        mainStage.setResizable(false);
        mainStage.setOnCloseRequest(t -> System.exit(0));
        Game.unfreeze.countDown();
    }

    public static void changePane(GridPane nextPane) {
        Platform.runLater(() -> mainStage.setScene(new Scene(nextPane, 300, 300)));
    }
}
