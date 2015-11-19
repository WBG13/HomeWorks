package TicTacToe.Controller;


import TicTacToe.View.GUIStageBuilder;

public class Main {
    public static void main(String[] args) {
        Thread game = new Thread(new Game(true, false));
        game.start();
        javafx.application.Application.launch(GUIStageBuilder.class);
    }
}
