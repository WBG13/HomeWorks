package TicTacToe.Model;

import TicTacToe.Controller.Game;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static java.lang.Thread.sleep;

public class Cell extends StackPane {
    public Text text = new Text();
    public Rectangle border;

    public Cell(int Y, int X) {

        final int posY = Y, posX = X;

        border = new Rectangle(100, 100);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(62));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (text.getText().equals("")) {
                    Game.selectedCellX = posX;
                    Game.selectedCellY = posY;
                    Game.unfreeze.countDown();
                }
            }
        });
    }
    public void win(){
        this.border.setFill(Color.GREENYELLOW);
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
