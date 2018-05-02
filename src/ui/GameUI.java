package ui;

import game.Player;

public interface GameUI {
    public void initRenderBoard(int[] squares, Player[] players);
    public void reRenderBoard();
    public void focusPlayer(Player player);
    public void roll();
    public void delay();
    public void setMessage(String message);

}
