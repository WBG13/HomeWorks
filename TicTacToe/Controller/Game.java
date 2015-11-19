package TicTacToe.Controller;

import TicTacToe.Model.Cell;
import TicTacToe.Model.Check;
import TicTacToe.View.GUIStageBuilder;
import TicTacToe.View.PaneBluePrints;

import java.util.concurrent.CountDownLatch;

public class Game implements Runnable {
    public static int selectedCellX, selectedCellY;
    public static String player1Name;
    public static String player2Name;
    public static Cell[][] field = new Cell[3][3];
    boolean player1AI, player2AI;
    public static CountDownLatch unfreeze = new CountDownLatch(1);
    int tie = 9;
    public static String result;


    Game(boolean pl1AI, boolean pl2AI) {
        player1AI = pl1AI;
        player2AI = pl2AI;
    }

    @Override
    public void run() {
        freeze();
        GUIStageBuilder.changePane(PaneBluePrints.firstPane());
        freeze();
        Player player1 = new Player("X", player1AI, player1Name);
        Player player2 = new Player("O", player2AI, player2Name);
        Player currentPlayer;

        while (true) {
            player1.move = !player1.move;
            if (player1.move) currentPlayer = player1;
            else currentPlayer = player2;
            if (currentPlayer.isHuman)
                freeze();
            else {
                currentPlayer.AI();
            }
            makeMove(currentPlayer.getSymbol(), selectedCellY, selectedCellX);
            tie--;
            if (tie == 0) {
                end("TIE");
            }
            try {
                if (Check.winner()) {
                    end("Player = " + currentPlayer.name + " win!!!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearField() {
        tie = 9;
        result = "";
        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                field[i][j].text.setText("");
            }
        }
    }

    public void makeMove(String player, int Y, int X) {
        field[Y][X].text.setText(player);
    }


    public static void freeze() {
        unfreeze = new CountDownLatch(1);
        try {
            unfreeze.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void luckyNumbers(int a, int b, int c, int d, int e, int f) {
        field[a][b].win();
        field[c][d].win();
        field[e][f].win();
    }

    public void end(String result) {
        Game.result = result;
        GUIStageBuilder.changePane(PaneBluePrints.finalPane());
        freeze();
        clearField();
        GUIStageBuilder.changePane(PaneBluePrints.gamePane());
    }
}
