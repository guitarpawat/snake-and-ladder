package ui;

import game.Player;
import presenter.GameInfo;
import presenter.GamePresenter;

public class ConsoleUI implements GameUI {

    private GamePresenter presenter;

    public ConsoleUI() {
        presenter = new GamePresenter(this,new GameInfo.GameCreater().addPlayer("Guitar").addPlayer("Sept").build());
        presenter.start();
    }

    @Override
    public void initRenderBoard(int[] squares, Player[] players) {

    }

    @Override
    public void reRenderBoard(Player player, int to, boolean dice) {

    }
}
