package TicTacToe.Controller;

import javafx.scene.text.Text;

public class Player {
    public boolean move = false, isHuman;
    private Text symbol;
    public String name;

    public Player(String symbol, boolean isHuman, String name) {
        this.name = name;
        this.isHuman = isHuman;
        this.symbol = new Text(symbol);
    }

    public String getSymbol() {
        return symbol.getText();
    }

    public void AI(){
        try {
            new AI(getSymbol());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
