package ui;

import game.Player;

public interface GameUI {
    public void startGame();
    public void initRenderBoard(int[] squares, Player[] players);
    public void reRenderBoard();
    public void focusPlayer(Player player);
    public void roll();
    public void setMessage(String message);
    public void gameEnded(Player winner);
    public void diceFace(int face);

}
