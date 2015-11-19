import javafx.scene.paint.Color;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * Created by TH-221 on 15.11.2015.
 */
public class Game implements Runnable {
    public static int selectedCellX, selectedCellY;
    static String player1Name, player2Name;
    public static Cell[][] field = new Cell[3][3];
    boolean player1AI, player2AI;
    static CountDownLatch unfreeze = new CountDownLatch(1);
    int tie = 9;
    static int [] p;
    static String result;


    Game(boolean pl1AI, boolean pl2AI) {
        player1AI = pl1AI;
        player2AI = pl2AI;
    }

    @Override
    public void run() {
        freeze();
        GUIStageBuilder.changePane(PaneBluePrints.firstPane());
        freeze();
        Player player1 = new Player("X", player1AI,player1Name);
        Player player2 = new Player("O", player2AI,player2Name);
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
                if (Check.Winner()) {
                    end("Player = " + currentPlayer.name + " win!!!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearField(){
        tie=9;
        result="";
        int i,j;
        for(i=0;i<3;i++){
            for(j=0;j<3;j++){
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
    public static void fill(int i, int j) throws InterruptedException {
        field[i][j].border.setFill(Color.GREEN);
        sleep(300);
    }
    public void end(String result){
        Game.result =result;
        GUIStageBuilder.changePane(PaneBluePrints.finalPane());
        freeze();
        clearField();
        GUIStageBuilder.changePane(PaneBluePrints.gamePane());
    }
}
