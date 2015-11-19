package TicTacToe.Model;

import TicTacToe.Controller.Game;

import java.util.Objects;

public class Check {
    public static boolean winner() throws InterruptedException {
        for (int x = 0; x < 3; x++) {
            if (!cellSymbol(x, 0, "") & cells(x, 0, x, 1) & cells(x, 1, x, 2)) {
                Game.luckyNumbers(x, 0, x, 1, x, 2);
                return true;
            }
            if (!cellSymbol(0, x, "") & cells(0, x, 1, x) & cells(1, x, 2, x)) {
                Game.luckyNumbers(0, x, 1, x, 2, x);
                return true;
            }
        }
        if (!cellSymbol(0, 0, "") & cells(0, 0, 1, 1) & cells(1, 1, 2, 2)) {
            Game.luckyNumbers(0, 0, 1, 1, 2, 2);
            return true;
        }
        if (!cellSymbol(0, 2, "") & cells(0, 2, 1, 1) & cells(1, 1, 2, 0)) {
            Game.luckyNumbers(0, 2, 1, 1, 2, 0);
            return true;
        }
        return false;
    }

    public static boolean cells(int y, int x, int s, int t) {
        return Objects.equals(Game.field[y][x].text.getText(), Game.field[s][t].text.getText());
    }

    public static boolean cellSymbol(int y, int x, String symbol) {
        return Objects.equals(Game.field[y][x].text.getText(), symbol);
    }
}

