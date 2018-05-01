package presenter;

import ui.GameUI;

public class GamePresenter {

    GameUI ui;
    GameInfo info;

    public GamePresenter(GameUI ui, GameInfo info) {
        this.ui = ui;
        this.info = info;
    }

    public void start() {
        ui.initRenderBoard(info.getBoardInfo(),info.getPlayers());
    }
}
