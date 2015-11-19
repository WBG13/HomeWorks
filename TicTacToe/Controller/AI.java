package TicTacToe.Controller;

import TicTacToe.Model.Check;

import java.util.Objects;

import static java.lang.Thread.sleep;

public class AI {
    private int x, y;

    AI(String currentSymbol) throws InterruptedException {
        sleep(500);
        analyzeField(currentSymbol);
        Game.selectedCellX = x;
        Game.selectedCellY = y;
    }

    public void analyzeField(String currentSymbol) {

        if (Objects.equals(Game.field[1][1].text.getText(), "")) makeDecision(1, 1);
        else if (findMyPosition(currentSymbol)) ;
        else if (checkEnemyPosition(currentSymbol)) ;
        else selectRandomPosition();
    }

    public boolean checkEnemyPosition(String cur) {
        switch (cur) {
            case ("X"):
                return findMyPosition("O");
            case ("O"):
                return findMyPosition("X");
        }
        return false;
    }

    public int findThirdCell(byte a, byte b) {
        int x;
        for (x = 0; x < 3; x++) {
            if (x != a & x != b) break;
        }
        return x;
    }

    public boolean findMyPosition(String symbol) {

        byte n, j, m, i;
        for (i = 0; i < 3; i++) {
            for (m = 0; m < 3; m++) {
                n = (byte) (1 + m);
                j = (byte) (1 + i);
                if (n > 2) n = 0;
                if (j > 2) j = 0;
                if (Check.cellSymbol(i, m, symbol) & Check.cells(i, m, i, n) & Check.cellSymbol(i, findThirdCell(n, m), "")) {
                    makeDecision(i, (findThirdCell(n, m)));
                    return true;
                }
                if (Check.cellSymbol(i, m, symbol) & Check.cells(i, m, j, m) & Check.cellSymbol(findThirdCell(i, j), m, "")) {
                    makeDecision(findThirdCell(i, j), m);
                    return true;
                }
                if (Check.cellSymbol(i, m, symbol) & Check.cells(i, m, j, n) & Check.cellSymbol(findThirdCell(i, j), findThirdCell(n, m), "")) {
                    makeDecision(findThirdCell(i, j), (findThirdCell(n, m)));
                    return true;
                }
                if ((2 - i == m) & Check.cellSymbol(i, m, symbol) & Check.cells(i, m, j, (findThirdCell(n, m))) & Check.cellSymbol(findThirdCell(i, j), n, "")) {
                    makeDecision(findThirdCell(i, j), n);
                    return true;
                }
            }
        }
        return false;
    }


    public void selectRandomPosition() {
        int x, y, z=0, line=0, pos;
        if (z<60) {pos = (int) (Math.random() * 9);z++;}
        else {pos=line++;}
        x = (byte) (pos % 3);
        y = (byte) (pos / 3);
        if (Objects.equals(Game.field[y][x].text.getText(), "")) {
            this.x = x;
            this.y = y;
    }
        else selectRandomPosition();
    }

    public void makeDecision(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

