package ui;

import game.Player;

public interface GameUI {
    public void initRenderBoard(int[] squares, Player[] players);
    public void reRenderBoard(Player player, int to, boolean dice);
}
