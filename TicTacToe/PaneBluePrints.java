import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PaneBluePrints {
    static GridPane firstPane() {
        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("Player 1 name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField("Player");
        grid.add(userTextField, 1, 1);

        Label userName2 = new Label("Player 2 name");
        grid.add(userName2, 0, 2);

        TextField userTextField2 = new TextField("CPU");
        grid.add(userTextField2, 1, 2);

        Button btn = new Button("Start");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(event -> {
            GUIStageBuilder.mainStage.setScene(new Scene(gamePane()));
            Game.unfreeze.countDown();
            Game.player1Name = userTextField.getText();
            Game.player2Name = userTextField2.getText();
        });

        grid.add(hbBtn, 1, 4);
        return grid;
    }

    static GridPane gamePane() {
        final GridPane grid = new GridPane();
        grid.setPrefSize(300, 300);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                Cell tile = new Cell(i, j);
                tile.setTranslateX(j * 100);
                tile.setTranslateY(i * 100);

                grid.getChildren().add(tile);

                Game.field[i][j] = tile;
            }
        }
        return grid;
    }

    public static GridPane finalPane() {
        final GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPadding(new Insets(70, 70, 70, 80));

        Text sceneTitle2 = new Text(Game.result);
        sceneTitle2.setFont(new Font(20));
        sceneTitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle2, 0, 0, 1, 1);


        Button btn3 = new Button("Retry");
        HBox hbBtn3 = new HBox(20);
        hbBtn3.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn3.getChildren().add(btn3);
        btn3.setOnAction(event ->
                Game.unfreeze.countDown());
        grid.add(hbBtn3, 0, 3);
        return grid;
    }
}
